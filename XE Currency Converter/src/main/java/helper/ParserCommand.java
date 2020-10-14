package helper;

import domain.entities.Money;
import enumeration.EnumCommands;

import java.math.BigDecimal;
import java.util.List;

public class ParserCommand {

    private EnumCommands command;
    private ExchangePair exchangePair;

    public ParserCommand(List<String> args) {

        this.command = EnumCommands.valueOf(args.get(0).toUpperCase());

        if (this.command.equals(EnumCommands.END)) {

            return;
        }

        Money moneyFrom = new Money(new BigDecimal(args.get(1)), args.get(2));
        String toCurrency = args.get(3);

        this.exchangePair = new ExchangePair(moneyFrom, toCurrency);
    }

    public EnumCommands getCommand() {
        return command;
    }

    public ExchangePair getExchangePair() {
        return exchangePair;
    }
}
