package domain.commands;

import domain.entities.Money;
import domain.external.ExchangeService;
import domain.io.Logger;
import external.CurrConvAPI;
import helper.ExchangePair;
import repository.ExchangeCacheMemory;

public class ConvertCommand implements Command {

    private ExchangePair exchangePair;
    private Logger logger;
    private CurrConvAPI currConv;
    private ExchangeService exchangeService;
    private ExchangeCacheMemory cacheMemory;

    public ConvertCommand(
            ExchangePair exchangePair,
            Logger logger,
            CurrConvAPI currConv,
            ExchangeService exchangeService,
            ExchangeCacheMemory cacheMemory) {

        this.exchangePair = exchangePair;
        this.logger = logger;
        this.currConv = currConv;
        this.exchangeService = exchangeService;
        this.cacheMemory = cacheMemory;
    }

    @Override
    public void execute() {
        Money exchangedMoney = exchangeService.exchange(exchangePair, currConv, logger, cacheMemory);

        if (exchangePair.getRate().matches(".*\\d.*")) {

            logger.logLine(String.format("Currency rate: %s", exchangePair.getRate()));
        }

        this.logger.logLine(exchangedMoney.toString());
    }

}
