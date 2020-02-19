package org.craigslist.helpers;

import com.google.common.base.CharMatcher;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class PricesHelper {

    public List<BigInteger> extractPrices(final List<String> allPrices) {
        return allPrices
                .stream()
                .map(this::stringToBigInt)
                .collect(Collectors.toList());
    }

    private BigInteger stringToBigInt(final String textInput) {
        final String digits = CharMatcher.inRange('0', '9').retainFrom(textInput);
        return BigInteger.valueOf(Integer.parseInt(digits));
    }

}
