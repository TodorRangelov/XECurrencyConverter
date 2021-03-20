package com.console.helper;

import com.domain.entities.Money;
import org.springframework.stereotype.Component;

@Component
public class ExchangePair {

    private Money from;
    private String toCurrency;
    private String rate;

    public ExchangePair() {
    }

    public ExchangePair(Money from, String toCurrency) {
        this.from = from;
        this.toCurrency = toCurrency;
    }

    public String getFromCurrency() {
        return this.from.getCurrency();
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public Money getFromMoney() {
        return from;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
