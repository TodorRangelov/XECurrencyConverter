package console;

import domain.commands.ConvertCommand;
import domain.commands.EndCommand;
import domain.io.Logger;
import external.CurrConvAPI;
import external.ExchangeMoney;
import helper.ExchangePair;
import helper.ParserCommand;

import java.util.List;

public class ConsoleCommandExecutor {

    private CurrConvAPI currConvExchangeService = new CurrConvAPI();
    private Logger logger = new ConsoleLogger();
    private ExchangeMoney exchangeMoney = new ExchangeMoney();

    public void execute(List<String> args) {

        ParserCommand parser = new ParserCommand(args);
        ExchangePair exchangePair = parser.getExchangePair();

        switch (parser.getCommand()) {

            case END:

                new EndCommand().execute();
                break;

            case CONVERT:

                new ConvertCommand(
                        exchangePair,
                        logger,
                        currConvExchangeService,
                        // Such a param should be of type ExchangeService or ExchangeMoney?
                        exchangeMoney).execute();
                break;
        }
    }

}
