package org.craigslist.steps.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.craigslist.pages.HomePage;

public class HomeSteps {

    private HomePage homePage = new HomePage();

    @Given("opened home page")
    public void openHomePage() {
        homePage.open();
    }

    @When("user click housing")
    public void userClickHousing() {
        homePage.clickHousingTitle();
    }
}
