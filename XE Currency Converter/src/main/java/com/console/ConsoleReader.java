package com.console;

import com.domain.io.Logger;
import com.helper.checkInput.CheckInput;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleReader {

    public String readCommandAndCheck(Scanner scanner,
                                      Logger logger,
                                      String userMessages,
                                      CheckInput checkInput) {

        while (true) {
            logger.logLine(userMessages);
            String line = scanner.nextLine().toUpperCase();

            if (checkInput.check(line)) {
                logger.logLine("Incorrect command! Try again.");

                continue;
            }

            return line;
        }
    }

}
