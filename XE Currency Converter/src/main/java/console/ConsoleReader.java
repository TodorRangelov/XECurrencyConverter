package console;

import helper.CheckConsoleInput;

import java.util.Scanner;

import static helper.InfoToInputDataHelper.*;

public class ConsoleReader {

    private String line;

    public String readCommandAndCheck(Scanner scanner, ConsoleLogger consoleLogger) {
        while (true) {

            consoleLogger.logLine(selectCommand());
            this.line = scanner.nextLine().toUpperCase();

            if (CheckConsoleInput.incorrectCommands(this.line)) {

                consoleLogger.logLine("Incorrect command! Try again.");
                continue;
            }

            return this.line;
        }
    }

    public String readValueAndCheck(Scanner scanner, ConsoleLogger consoleLogger) {
        while (true) {

            consoleLogger.logLine(value());
            this.line = scanner.nextLine();

            if (CheckConsoleInput.incorrectValue(this.line)) {

                consoleLogger.logLine("Incorrect Value! Try again.");
                continue;
            }

            return this.line;
        }
    }

    public String readCurrencyAndCheck(Scanner scanner, ConsoleLogger consoleLogger) {
        while (true) {

            consoleLogger.logLine(selectCurrencyConvert());
            this.line = scanner.nextLine().toUpperCase();

            if (CheckConsoleInput.incorrectCurrency(this.line)) {
                consoleLogger.logLine("Incorrect currency! Try again.");
                continue;
            }
            return this.line;
        }
    }

    public String readCurrencyToAndCheck(Scanner scanner, ConsoleLogger consoleLogger) {
        while (true) {
            consoleLogger.logLine(selectToCurrency());
            this.line = scanner.nextLine().toUpperCase();

            if (CheckConsoleInput.incorrectCurrency(this.line)) {
                consoleLogger.logLine("Incorrect currency! Try again.");
                continue;
            }
            return this.line;
        }
    }

}
