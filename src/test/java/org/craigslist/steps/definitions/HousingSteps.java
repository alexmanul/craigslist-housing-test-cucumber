package org.craigslist.steps.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.craigslist.helpers.housing.SortingHelper;
import org.craigslist.pages.HousingPage;

import java.util.List;

public class HousingSteps {
    private HousingPage housingPage = new HousingPage();
    private SortingHelper sortingHelper = new SortingHelper();

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
       housingPage.validateDefaultPriceFilterOptions(expectedOptions);
    }

    @When("user pick price sorting filter: {string}")
    public void pickPriceSorting(String filterOption) {
        housingPage.clickPriceDropDown();
        housingPage.pickPriceSorting(filterOption);
    }

    @Then("validate items by price up")
    public void validateItemsByPriceUp() {
    }
}
