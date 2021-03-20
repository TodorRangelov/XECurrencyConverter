package com.console.helper;

import org.springframework.stereotype.Component;

@Component
public class UserMessagesHelper {

    public UserMessagesHelper() {
    }

    public String selectCommand() {
        return String.format("%nSelect command:%nCONVERT%nREGISTER USER%nEND");
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
