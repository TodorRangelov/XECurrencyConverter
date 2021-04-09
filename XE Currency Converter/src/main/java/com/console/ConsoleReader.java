package com.console;

import com.domain.io.Logger;
import com.helper.checkInput.CheckInput;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleReader {

    public String readCommandConvertLogoutOrEndAndCheck(Scanner scanner,
                                                        Logger logger,
                                                        String userMessages,
                                                        CheckInput checkInput) {

        while (true) {
            logger.logLine(userMessages);
            String line = scanner.nextLine().toUpperCase();

            switch (line) {
                case "C":
                    line = "CONVERT";
                    break;
                case "L":
                    line = "LOGOUT";
                    break;
                case "H":
                    line = "HISTORY";
                    break;
                case "E":
                    line = "END";
                    break;
            }

            if (checkInput.check(line)) {
                logger.logLine("Incorrect command! Try again.");

                continue;
            }

            return line;
        }
    }

    public String readCommandRegisterLoginOrEndAndCheck(Scanner scanner,
                                                        Logger logger,
                                                        String userMessages,
                                                        CheckInput checkInput) {

        while (true) {
            logger.logLine(userMessages);
            String line = scanner.nextLine().toUpperCase();

            switch (line) {
                case "R":
                    line = "REGISTER";
                    break;
                case "L":
                    line = "LOGIN";
                    break;
                case "T":
                    line = "TEST";
                    break;
                case "END":
                    line = "END";
                    break;
            }

            if (checkInput.check(line)) {
                logger.logLine("Incorrect command! Try again.");

                continue;
            }

            return line;
        }
    }

}
