package org.craigslist.steps.definitions;

import com.codeborne.selenide.Screenshots;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Hooks {

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                File screenshot = Screenshots.takeScreenShotAsFile();
                InputStream targetStream = new FileInputStream(screenshot);
                Allure.addAttachment("FailingCase", "image/png", targetStream, "png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
