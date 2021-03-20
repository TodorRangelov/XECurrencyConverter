package com.console.helper;

public class ParseRate {

    public static String parseRateToString(String response) {

        int columnIdx = response.lastIndexOf(':');
        int closingBracketIdx = response.lastIndexOf('}');

        String exchangeRate = response.substring(columnIdx + 1, closingBracketIdx);

        return (exchangeRate);
    }

}
