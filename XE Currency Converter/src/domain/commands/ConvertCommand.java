package domain.commands;

import console.ConsoleLogger;
import domain.entities.Money;
import domain.extenal.ExchangeService;
import domain.io.Logger;
import external.CurrConvExchangeService;

import java.math.BigDecimal;

public class ConvertCommand implements Command {

    private Money from;
    private String toCurrency;
    private ExchangeService exchangeService;
    private Logger logger;

    public ConvertCommand(String[] args) {
        this.from = new Money(new BigDecimal(args[1]), args[2]);
        this.toCurrency = args[3];
        this.exchangeService = new CurrConvExchangeService();
        this.logger = new ConsoleLogger();
    }

    @Override
    public void execute() {

        Money exchangedMoney = exchangeService.exchange(this.from, this.toCurrency);
        this.logger.logLine(exchangedMoney.toString());

    }
}
