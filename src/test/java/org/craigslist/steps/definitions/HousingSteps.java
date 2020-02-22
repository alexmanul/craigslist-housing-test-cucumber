package org.craigslist.steps.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.craigslist.helpers.PricesHelper;
import org.craigslist.pages.HousingPage;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.List;

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

    @When("click drop down menu with price sorting options")
    public void clickDropDown() {
        housingPage.clickPriceDropDown();
    }

    @Then("should be available following sorting options:")
    public void defaultSortingOptions(final List<String> expectedOptions) {
        final List<String> actualOptions = housingPage.getActualOptions();
        assertThat(actualOptions)
                .withFailMessage("Sorting options mismatch" +
                        "\n actual result:   " + actualOptions +
                        "\n expected result: " + expectedOptions)
                .isEqualTo(expectedOptions);
    }

    @When("user pick price sorting filter: {string}")
    public void pickPriceSorting(final String filterOption) {
        housingPage.clickPriceDropDown();
        housingPage.pickPriceSorting(filterOption);
    }

    @Then("validate {string} sorting")
    public void validateSorting(final String sorting) {
        final List<String> pricesWithCurrency = housingPage.getAllPrices();
        final List<String> housesTitles = housingPage.getAllPricesTitles();
        final List<BigInteger> allPrices = pricesHelper.extractIntegers(pricesWithCurrency);

        if (sorting.equals("hinta ↑")) {
            pricesHelper.assertHouseSortingByPriceUp(allPrices, housesTitles, pricesWithCurrency);
        } else if (sorting.equals("hinta ↓")) {
            pricesHelper.assertHouseSortingByPriceDown(allPrices, housesTitles, pricesWithCurrency);
        } else {
            throw new InvalidParameterException("available sorting options are: 'hinta ↑' and 'hinta ↓'");
        }
    }

    @And("set nearby areas")
    public void setNearbyAreas() {
        housingPage.setNearbyAreas();
    }

    @When("after {string} search")
    public void afterSearch(String searchText) {
        housingPage.searchFor(searchText);
    }
}
