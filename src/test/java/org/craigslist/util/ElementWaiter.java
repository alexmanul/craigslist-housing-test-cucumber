package org.craigslist.util;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Condition.appear;

public class ElementWaiter {

    private ElementWaiter() {
        throw new IllegalStateException();
    }

    private static final Integer DEF_WAIT_TIME_MILLIS = 8000;

    public static boolean isVisible(SelenideElement element, Integer timeoutInMillis) {
        try {
            element.waitUntil(appear, timeoutInMillis);
            return true;
        } catch (ElementNotFound ignore) {
            return false;
        }
    }

    public static boolean isVisible(SelenideElement element) {
       return isVisible(element, DEF_WAIT_TIME_MILLIS);
    }

}
