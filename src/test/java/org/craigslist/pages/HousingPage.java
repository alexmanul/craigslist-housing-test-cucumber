package org.craigslist.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.java.Log;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.SoftAssertions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.craigslist.configuration.PropertiesConfiguration.getHomeBaseUrl;
import static org.craigslist.configuration.PropertiesConfiguration.getHousingPagePath;
import static org.craigslist.util.ElementWaiter.isVisible;

@Log
public class HousingPage {
    private final SelenideElement NEARBY_AREAS_CHECKBOX = $(".searchNearby");
    private final SelenideElement SEARCH_FIELD = $("#query");
    private final SelenideElement SORTABLE_RESULTS = $("#sortable-results");
    private final SelenideElement SEARCH_OPTION_CONTAINER = $(".search-options-container");

    private final SelenideElement FILTER_DROP_DOWN_MENU = $(".search-sort > .dropdown");
    private final ElementsCollection FILTER_DROP_DOWN_MENU_EXPANDED = $$(".dropdown-list.dropdown-show > .dropdown-item.mode");
    private final ElementsCollection HOUSING_TITLES = $$(".result-title.hdrlnk");
    private final ElementsCollection HOUSING_PRICES = $$(".result-info .result-price");

    private final SelenideElement FILTER_NEWEST = $("[data-selection=\"date\"]");
    private final SelenideElement FILTER_PRICE_UP = $("[data-selection=\"priceasc\"]");
    private final SelenideElement FILTER_PRICE_DOWN = $("[data-selection=\"pricedsc\"]");

    private Map<String, SelenideElement> filterMap() {
        Map<String, SelenideElement> map = new HashMap<>();
        map.put("uusin", FILTER_NEWEST);
        map.put("hinta ↑", FILTER_PRICE_UP);
        map.put("hinta ↓", FILTER_PRICE_DOWN);

        return map;
    }

    public void isPageLoaded() {
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(isVisible(SEARCH_FIELD))
                .withFailMessage("Page not loaded: search field is not visible")
                .isTrue();
        softly.assertThat(isVisible(SORTABLE_RESULTS))
                .withFailMessage("Page not loaded: sortable results are not visible")
                .isTrue();
        softly.assertThat(isVisible(SEARCH_OPTION_CONTAINER))
                .withFailMessage("Page not loaded: search options are not visible")
                .isTrue();
        softly.assertThat(url()).isEqualTo(getHomeBaseUrl() + getHousingPagePath());
        softly.assertAll();
    }

    public void open() {
        log.info("Opening housing page: " + getHomeBaseUrl() + getHousingPagePath());
        Selenide.open(getHomeBaseUrl() + getHousingPagePath());
    }

    public void clickPriceDropDown() {
        FILTER_DROP_DOWN_MENU.shouldBe(visible).click();
    }

    public List<String> getActualOptions() {
        return FILTER_DROP_DOWN_MENU_EXPANDED
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public void pickPriceSorting(final String filterOption) {
        filterMap().get(filterOption).shouldBe(visible).click();
    }

    public List<String> getAllPricesTitles() {
        return HOUSING_TITLES
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAllPrices() {
        return HOUSING_PRICES
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public void setNearbyAreas() {
        NEARBY_AREAS_CHECKBOX.shouldBe(visible).click();
    }

    public void searchFor(String searchText) {
        SEARCH_FIELD.shouldBe(visible);

        if (searchText.equals("random")) {
            var randomText = "THIS IS SPARTAAA " + RandomStringUtils.random(10, true, false);
            SEARCH_FIELD.sendKeys(randomText);
        } else {
            SEARCH_FIELD.sendKeys(searchText);
        }
        SEARCH_FIELD.submit();
    }
}
