package com.helper.checkInput;

public class IncorrectValue implements CheckInput {

    @Override
    public boolean check(String input) {
        return !input.matches(".*\\d.*");
    }
}
