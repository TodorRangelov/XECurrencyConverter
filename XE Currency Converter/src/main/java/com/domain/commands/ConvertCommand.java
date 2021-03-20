package com.domain.commands;

import com.domain.entities.Money;
import com.service.MoneyService;
import com.domain.io.Logger;
import com.external.CurrConvAPI;
import com.console.helper.ExchangePair;
import com.repository.ExchangeCacheMemoryImpl;

public class ConvertCommand implements Command {

    private ExchangePair exchangePair;
    private Logger logger;
    private CurrConvAPI currConv;
    private MoneyService exchangeService;
    private ExchangeCacheMemoryImpl cacheMemory;

    public ConvertCommand(
            ExchangePair exchangePair,
            Logger logger,
            CurrConvAPI currConv,
            MoneyService exchangeService,
            ExchangeCacheMemoryImpl cacheMemory) {

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
