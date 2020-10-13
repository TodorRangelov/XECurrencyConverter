package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleRunner {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ConsoleCommandExecutor commandExecutor = new ConsoleCommandExecutor();
        ConsoleLogger consoleLogger = new ConsoleLogger();
        ConsoleReader reader = new ConsoleReader();

        while (true) {
            List<String> args = new ArrayList<>();

            args.add(reader.readCommandAndCheck(scanner, consoleLogger));

            if (args.get(0).equals("END")) {

                commandExecutor.execute(args);
            }

            args.add(reader.readValueAndCheck(scanner, consoleLogger));

            args.add(reader.readCurrencyAndCheck(scanner, consoleLogger));

            args.add(reader.readCurrencyToAndCheck(scanner, consoleLogger));

            try {
                commandExecutor.execute(args);
            } catch (Exception e) {
                consoleLogger.logLine("something went wrong during HTTP request to external API");
            }
        }
    }

}
