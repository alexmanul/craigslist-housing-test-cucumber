package org.craigslist.steps.definitions;

import io.cucumber.java.en.Then;
import org.craigslist.pages.HousingPage;

public class HousingSteps {
    private HousingPage housingPage = new HousingPage();

    @Then("housing page is opened")
    public void housingPageOpened() {
        housingPage.isPageLoaded();
    }
}
