package console;

import helper.checkInput.*;
import helper.UserMessagesHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleRunner {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ConsoleCommandExecutor commandExecutor = new ConsoleCommandExecutor();
        ConsoleLogger consoleLogger = new ConsoleLogger();
        ConsoleReader reader = new ConsoleReader();
        UserMessagesHelper messages = new UserMessagesHelper();

        while (true) {
            List<String> args = new ArrayList<>();

            args.add(reader.readCommandAndCheck(
                    scanner,
                    consoleLogger,
                    messages.selectCommand(),
                    new IncorrectCommands()));

            if (args.get(0).equals("END")) {
                commandExecutor.execute(args);
            }

            args.add(reader.readCommandAndCheck(
                    scanner,
                    consoleLogger,
                    messages.value(),
                    new IncorrectValue()));

            args.add(reader.readCommandAndCheck(
                    scanner,
                    consoleLogger,
                    messages.selectCurrencyConvert(),
                    new IncorrectCurrency()));

            args.add(reader.readCommandAndCheck(
                    scanner,
                    consoleLogger,
                    messages.selectToCurrency(),
                    new IncorrectCurrency()));

            try {
                commandExecutor.execute(args);
            } catch (Exception e) {
                consoleLogger.logLine("something went wrong during HTTP request to external API");
            }
        }
    }

}
