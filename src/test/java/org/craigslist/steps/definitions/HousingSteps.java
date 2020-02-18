package org.craigslist.steps.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.craigslist.helpers.PricesHelper;
import org.craigslist.pages.HousingPage;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class HousingSteps {
    private HousingPage housingPage = new HousingPage();
    private PricesHelper pricesHelper = new PricesHelper();

    @Then("housing page is opened")
    public void housingPageOpened() {
        housingPage.isPageLoaded();
    }

    @Given("opened housing page")
    public void openedHousingPage() {
        housingPage.open();
    }

    @When("user click drop down menu with price sorting options")
    public void clickDropDown() {
        housingPage.clickPriceDropDown();
    }

    @Then("by default should be following options:")
    public void defaultSortingOptions(List<String> expectedOptions) {
        //todo make assertions here
        housingPage.validateDefaultPriceFilterOptions(expectedOptions);
    }

    @When("user pick price sorting filter: {string}")
    public void pickPriceSorting(String filterOption) {
        housingPage.clickPriceDropDown();
        housingPage.pickPriceSorting(filterOption);
    }

    @And("pick only house(s) for {string}")
    public void pickOnlyHouseFor(String country) {
        //todo
        open("https://helsinki.craigslist.org/search/hhh?sort=priceasc&availabilityMode=0&searchNearby=1&lang=en&cc=gb");
    }

    @Then("validate sorting price up")
    public void validateSortingPriceUp() {
        final List<String> allPricesTitles = housingPage.getAllPricesTitles();
        System.out.println(allPricesTitles);

        List<BigInteger> allPrices = pricesHelper.extractPrices(allPricesTitles);
        System.out.println(allPrices);

        assertThat(allPrices).isSortedAccordingTo(Comparator.naturalOrder());
    }
}
