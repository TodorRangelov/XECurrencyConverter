package com.service;

import com.domain.entities.Money;
import com.external.CurrConvAPI;
import com.domain.io.Logger;
import com.console.helper.ExchangePair;
import com.repository.ExchangeCacheMemoryImpl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MoneyServiceImpl implements MoneyService {

    @Override
    public Money exchange(ExchangePair exchangePair,
                          CurrConvAPI currConv,
                          Logger logger,
                          ExchangeCacheMemoryImpl cacheMemory) {

        String rate;

        if (cacheMemory.isMemoryExpired(exchangePair)) {
            rate = currConv.fetchExchangeRateFor(exchangePair);

            cacheMemory.putValue(exchangePair, rate);

        } else {
            rate = cacheMemory.getValue(exchangePair);
        }

        exchangePair.setRate(rate);

        BigDecimal rateBigDecimal = new BigDecimal(rate);

        BigDecimal exchangedValue = exchangePair.getFromMoney().getValue().multiply(rateBigDecimal);

        return new Money(exchangedValue, exchangePair.getToCurrency());
    }
}
