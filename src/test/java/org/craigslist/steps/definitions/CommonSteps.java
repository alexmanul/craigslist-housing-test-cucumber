package org.craigslist.steps.definitions;

import io.cucumber.java.en.Then;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.craigslist.configuration.PropertiesConfiguration.getHomeBaseUrl;

public class CommonSteps {

    @Then("url should contain following path: {string}")
    public void checkUrlPath(String expectedPath) {
        assertThat(url()).isEqualTo(getHomeBaseUrl() + expectedPath);
    }
}
