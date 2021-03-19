package console;

import enumeration.EnumCommands;
import external.CurrConvAPI;
import external.ExchangeMoney;
import helper.ExchangePair;
import helper.ParserCommand;
import helper.checkInput.*;
import helper.UserMessagesHelper;
import repository.ExchangeCacheMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleRunner {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ConsoleLogger consoleLogger = new ConsoleLogger();
        ConsoleReader reader = new ConsoleReader();
        UserMessagesHelper messages = new UserMessagesHelper();
        CurrConvAPI currConvAPI = new CurrConvAPI();
        ExchangeMoney exchangeMoney = new ExchangeMoney();
        ExchangeCacheMemory cacheMemory = new ExchangeCacheMemory();
        ConsoleCommandExecutor commandExecutor = new ConsoleCommandExecutor(
                currConvAPI,
                consoleLogger,
                exchangeMoney,
                cacheMemory);
        ExchangePair exchangePair = new ExchangePair();

        while (true) {
            List<String> args = new ArrayList<>();

            args.add(reader.readCommandAndCheck(
                    scanner,
                    consoleLogger,
                    messages.selectCommand(),
                    new IncorrectCommands()));

            ParserCommand parser = new ParserCommand(args);

            if (args.get(0).equals("END")) {
                commandExecutor.execute(parser.getCommand().toString(), exchangePair);
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

            parser = new ParserCommand(args);
            exchangePair = parser.getExchangePair(args);


            try {
                commandExecutor.execute(parser.getCommand().toString(), exchangePair);
            } catch (Exception e) {
                consoleLogger.logLine("something went wrong during HTTP request to external API");
            }
        }
    }

}
