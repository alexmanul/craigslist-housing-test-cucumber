package org.craigslist.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.java.Log;
import org.assertj.core.api.SoftAssertions;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.craigslist.configuration.PropertiesConfiguration.getHomeBaseUrl;
import static org.craigslist.configuration.PropertiesConfiguration.getHousingPagePath;
import static org.craigslist.util.ElementWaiter.isVisible;

@Log
public class HousingPage {
    private final SelenideElement SEARCH_FIELD = $(".flatinput.ui-autocomplete-input");
    private final SelenideElement SORTABLE_RESULTS = $("#sortable-results");
    private final SelenideElement SEARCH_OPTION_CONTAINER = $(".search-options-container");

    private final SelenideElement FILTER_DROP_DOWN_MENU = $(".search-sort > .dropdown");
    private final ElementsCollection FILTER_DROP_DOWN_MENU_EXPANDED = $$(".dropdown-list.dropdown-show > .dropdown-item.mode");

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

    public void clickDropDown() {
        FILTER_DROP_DOWN_MENU.shouldBe(visible).click();
    }

    public void validateDefaultSearchOptions(final List<String> expectedOption) {
        List<String> actualOptions = FILTER_DROP_DOWN_MENU_EXPANDED
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());

        assertThat(actualOptions).isEqualTo(expectedOption);
    }
}
