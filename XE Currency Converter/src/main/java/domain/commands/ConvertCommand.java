package domain.commands;

import domain.entities.Money;
import domain.external.ExchangeService;
import domain.io.Logger;
import external.CurrConvAPI;
import helper.ExchangePair;

public class ConvertCommand implements Command {


    private ExchangePair exchangePair;
    private Logger logger;
    private CurrConvAPI currConv;
    private ExchangeService exchangeService;

    public ConvertCommand(
            ExchangePair exchangePair,
            Logger logger,
            CurrConvAPI currConv,
            ExchangeService exchangeService) {

        this.exchangePair = exchangePair;
        this.logger = logger;
        this.currConv = currConv;
        this.exchangeService = exchangeService;
    }

    @Override
    public void execute() {
        Money exchangedMoney = exchangeService.exchange(exchangePair, currConv, logger);

        if (exchangePair.getRate().matches(".*\\d.*")) {

            logger.logLine(String.format("Currency rate: %s", exchangePair.getRate()));
        }

        this.logger.logLine(exchangedMoney.toString());
    }

}
