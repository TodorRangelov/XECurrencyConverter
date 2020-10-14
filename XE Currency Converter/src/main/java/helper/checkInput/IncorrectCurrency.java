package helper.checkInput;

public class IncorrectCurrency implements CheckInput {

    @Override
    public boolean check(String input) {
        if (input.length() == 3 && !input.matches(".*\\d.*")) {
            return false;
        }

        return true;
    }
}
