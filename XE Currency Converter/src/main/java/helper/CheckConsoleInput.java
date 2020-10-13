package helper;

import enumeration.EnumCommands;

public class CheckConsoleInput {

    public static boolean incorrectCommands(String command) {

        try {
            EnumCommands enumCommands = EnumCommands.valueOf(command);

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
