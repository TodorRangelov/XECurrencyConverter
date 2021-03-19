package com.helper;

public class UserMessagesHelper {

    public UserMessagesHelper() {
    }

    public String selectCommand() {
        return String.format("%nSelect command: CONVERT or END.");
    }

    public String selectCurrencyConvert() {
        return "Select currency which will convert.";
    }

    public String value() {
        return "Value:";
    }

    public String selectToCurrency() {
        return "To currency.";
    }
}
