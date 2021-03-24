package com.console.helper;

import org.springframework.stereotype.Component;

@Component
public class UserMessagesHelper {

    public UserMessagesHelper() {
    }

    public String selectCommand() {
        return String.format("%nSelect command:%nCONVERT press C%nLOGOUT press L%nEND press E");
    }

    public String selectCommandRegisterLoginOrEnd() {
        return String.format("%nSelect command:%nREGISTER USER press R%nLOGIN USER press L%nEND press E");
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
