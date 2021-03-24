package com.domain.commands;

import com.domain.entities.Money;
import com.service.MoneyService;
import com.domain.io.Logger;
import com.external.CurrConvAPI;
import com.console.helper.ExchangePair;
import com.repository.ExchangeCacheMemoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ConvertCommand {

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

    public List<String> execute() {
        Money exchangedMoney = exchangeService.exchange(exchangePair, currConv, logger, cacheMemory);

        List<String> s = new ArrayList<>();

        if (exchangePair.getRate().matches(".*\\d.*")) {

            s.add(String.format("%s", exchangePair.getRate()));
            s.add(String.format("Currency rate: %s", exchangePair.getRate()));
        }

        s.add(exchangedMoney.toString());
        s.add(String.format("%.2f", exchangedMoney.getValue()));

        return s;
    }

}
