package org.craigslist.pages;

import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Selenide.$;
import static org.craigslist.util.ElementWaiter.isVisible;

public class HousingPage {
    private final SelenideElement SEARCH_FIELD = $(".flatinput.ui-autocomplete-input");
    private final SelenideElement SORTABLE_RESULTS = $("#sortable-results");
    private final SelenideElement SEARCH_OPTION_CONTAINER = $(".search-options-container");

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
        softly.assertAll();
    }
}
