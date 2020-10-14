package helper.checkInput;

import enumeration.EnumCommands;

public class IncorrectCommands implements CheckInput {

    @Override
    public boolean check(String input) {
        try {
            EnumCommands enumCommands = EnumCommands.valueOf(input);

        } catch (IllegalArgumentException e) {
            return true;
        }

        return false;
    }
}
