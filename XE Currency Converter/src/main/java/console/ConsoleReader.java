package console;

import domain.io.Logger;
import helper.checkInput.CheckInput;

import java.util.Scanner;

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
