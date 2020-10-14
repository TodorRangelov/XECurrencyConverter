package console;

import domain.commands.ConvertCommand;
import domain.commands.EndCommand;
import domain.io.Logger;
import enumeration.EnumCommands;
import external.CurrConvAPI;
import external.ExchangeMoney;
import helper.ExchangePair;
import repository.ExchangeCacheMemory;

public class ConsoleCommandExecutor {

    private CurrConvAPI currConvExchangeService;
    private Logger logger;
    private ExchangeMoney exchangeMoney;
    private ExchangeCacheMemory cacheMemory;

    public ConsoleCommandExecutor(CurrConvAPI currConvExchangeService,
                                  Logger logger,
                                  ExchangeMoney exchangeMoney,
                                  ExchangeCacheMemory cacheMemory) {
        this.currConvExchangeService = currConvExchangeService;
        this.logger = logger;
        this.exchangeMoney = exchangeMoney;
        this.cacheMemory = cacheMemory;
    }

    public void execute(EnumCommands command, ExchangePair exchangePair) {

        switch (command) {
            case END:
                new EndCommand().execute();
                break;

            case CONVERT:
                new ConvertCommand(
                        exchangePair,
                        logger,
                        currConvExchangeService,
                        exchangeMoney,
                        cacheMemory).execute();
                break;
        }
    }

}
