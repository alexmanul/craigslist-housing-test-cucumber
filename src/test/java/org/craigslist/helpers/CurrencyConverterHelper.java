package org.craigslist.helpers;


import javax.money.Monetary;
import javax.money.convert.MonetaryConversions;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryFormats;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class CurrencyConverterHelper {

    public BigDecimal convertCurrencyInCents(final BigDecimal amount, final String from, final String to) {
        var fromCurrency = Monetary.getDefaultAmountFactory().setCurrency(from)
                .setNumber(amount).create();

        var conversion = MonetaryConversions.getConversion(to);
        var convertedAmount = fromCurrency.with(conversion).multiply(100);

        var customFormat = MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder
                .of(Locale.US)
                .set("pattern", "0.00").build());
        String convertedMoney = customFormat.format(convertedAmount);

        return new BigDecimal(convertedMoney)
                .setScale(0, RoundingMode.HALF_DOWN);
    }

    public String getAlphabeticCode(String currency) {
        String mappedCurrency;
        switch (currency) {
            case "руб":
                mappedCurrency = "RUB";
                break;
            case "SEK":
                mappedCurrency = "SEK";
                break;
            case "€":
                mappedCurrency = "EUR";
                break;
            default:
                throw new IllegalStateException("Unexpected currency: " + currency);
        }
        return mappedCurrency;
    }
}
