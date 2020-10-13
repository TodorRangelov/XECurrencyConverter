package helper;

public class InfoToInputDataHelper {

    public InfoToInputDataHelper() {
    }

    public static String selectCommand() {
        return String.format("%nSelect command: CONVERT or END.");
    }

    public static String selectCurrencyConvert() {
        return "Select currency which will convert.";
    }

    public static String value() {
        return "Value:";
    }

    public static String selectToCurrency() {
        return "To currency.";
    }

}
