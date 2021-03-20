package com.helper.checkInput;

import com.domain.EnumCommands;

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
