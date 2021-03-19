package domain.entities;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal value;
    private final String currency;

    public Money(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {

        return String.format("Money value = %.2f currency = '%s'%n", getValue(), getCurrency());
    }
}
