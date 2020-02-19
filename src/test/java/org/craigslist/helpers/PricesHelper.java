package org.craigslist.helpers;

import com.google.common.base.CharMatcher;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PricesHelper {

    public List<BigInteger> extractIntegers(final List<String> allPrices) {
        return allPrices
                .stream()
                .map(this::stringToBigInt)
                .collect(Collectors.toList());
    }

    private BigInteger stringToBigInt(final String textInput) {
        final String digits = CharMatcher.inRange('0', '9').retainFrom(textInput);
        return BigInteger.valueOf(Integer.parseInt(digits));
    }


    public void assertHouseSortingByPriceUp(final List<BigInteger> allPrices,
                                            final List<String> housesTitles,
                                            final List<String> pricesWithCurrency) {
        for (int i = 0; i < allPrices.size(); i++) {

            int tc = 1;
            if (i == allPrices.size() - 1) {
                tc = 0;
            }

            assertThat(allPrices.get(i))
                    .withFailMessage("\nExpecting that house with position " + i + ":\n" +
                            "and with title: [" + housesTitles.get(i) + "]\n" +
                            "and price: [" + pricesWithCurrency.get(i) + "]\n" +
                            "should be less or equal to:\n" +
                            "house with title: [" + housesTitles.get(i + tc) + "]\n" +
                            "and price: [" + pricesWithCurrency.get(i + tc) + "]")
                    .isLessThanOrEqualTo(allPrices.get(i + tc));
        }
    }

    public void assertHouseSortingByPriceDown(final List<BigInteger> allPrices,
                                              final List<String> housesTitles,
                                              final List<String> pricesWithCurrency) {
        for (int i = 0; i < allPrices.size(); i++) {

            int tc = 1;
            if (i == allPrices.size() - 1) {
                tc = 0;
            }

            assertThat(allPrices.get(i))
                    .withFailMessage("\nExpecting that house with position " + i + ":\n" +
                            "and with title: [" + housesTitles.get(i) + "]\n" +
                            "and price: [" + pricesWithCurrency.get(i) + "]\n" +
                            "should be higher or equal to:\n" +
                            "house with title: [" + housesTitles.get(i + tc) + "]\n" +
                            "and price: [" + pricesWithCurrency.get(i + tc) + "]")
                    .isGreaterThanOrEqualTo(allPrices.get(i + tc));
        }
    }
}
