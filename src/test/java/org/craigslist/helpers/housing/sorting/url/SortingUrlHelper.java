package org.craigslist.helpers.housing.sorting.url;

import static org.craigslist.configuration.PropertiesConfiguration.getHomeBaseUrl;

//todo do i need this?
public class SortingUrlHelper {
    private static final String NEWEST_ITEMS_PATH = "/search/hhh?sort=date";
    private static final String UP_PRICE_ITEMS_PATH = "/search/hhh?sort=priceasc";
    private static final String LOW_PRICE_ITEMS_PATH = "/search/hhh?sort=pricedsc";
    private static final String ONLY_FINLAND_COUNTRY_PARAMS = "&searchNearby=1&availabilityMode=0&sale_date=kaikki+pvmt";

    public String byNewest() {
        return getHomeBaseUrl() + NEWEST_ITEMS_PATH;
    }

    public String byPriceUp() {
        return getHomeBaseUrl() + UP_PRICE_ITEMS_PATH;
    }

    public String byPriceDown() {
        return getHomeBaseUrl() + LOW_PRICE_ITEMS_PATH;
    }
}

