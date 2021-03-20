package com.helper.checkInput;

import com.domain.EnumCommands;

public class CheckConsoleInput {

    private String input;

    public boolean incorrectCommands() {

        try {
            EnumCommands enumCommands = EnumCommands.valueOf(input);

        } catch (IllegalArgumentException e) {
            return true;
        }

        return false;
    }


    public static boolean incorrectValue(String value) {

        return !value.matches(".*\\d.*");
    }

    public static boolean incorrectCurrency(String currency) {

        if (currency.length() == 3 && !currency.matches(".*\\d.*")) {
            return false;
        }

        return true;
    }

}
