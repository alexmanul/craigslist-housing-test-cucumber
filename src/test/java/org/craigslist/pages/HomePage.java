package org.craigslist.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.craigslist.configuration.PropertiesConfiguration.getHomeBaseUrl;

public class HomePage {
    private final SelenideElement CRAIGSLIST_LOGO = $("#logo");
    private final SelenideElement HOUSING_TITLE = $("#hhh");

    public void open() {
        Selenide.open(getHomeBaseUrl());
        CRAIGSLIST_LOGO.shouldBe(visible);
    }

    public void clickHousingTitle() {
        HOUSING_TITLE.shouldBe(visible).click();
    }
}
