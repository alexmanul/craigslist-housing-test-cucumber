package org.craigslist.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber-report.json"},
        strict = true,
        stepNotifications = true,
        features = "classpath:features",
        glue = "org.craigslist.steps.definitions",
        tags = "@all"
)
public class DefaultTestRunner extends AbstractTestRunner {
}