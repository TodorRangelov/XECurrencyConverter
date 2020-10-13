package external;

import domain.entities.Money;
import domain.external.ExchangeService;
import domain.io.Logger;
import helper.ExchangePair;
import repository.ExchangeCacheMemory;

import java.math.BigDecimal;

public class ExchangeMoney implements ExchangeService {

    ExchangeCacheMemory cacheMemory = new ExchangeCacheMemory();

    @Override
    public Money exchange(ExchangePair exchangePair,
                          CurrConvAPI currConv,
                          Logger logger) {

        String rate;

        if (cacheMemory.isMemoryExpired(exchangePair)) {
            rate = currConv.fetchExchangeRateFor(exchangePair);

            cacheMemory.putValue(exchangePair, rate);

        } else {
            rate = cacheMemory.getValue(exchangePair);
        }

        exchangePair.setRate(rate);

//        if (rate.matches(".*\\d.*")) {
//
//            logger.logLine(String.format("Currency rate: %s", rate));
//        }

        BigDecimal rateBigDecimal = new BigDecimal(rate);

        BigDecimal exchangedValue = exchangePair.getFromMoney().getValue().multiply(rateBigDecimal);

        return new Money(exchangedValue, exchangePair.getToCurrency());
    }
}
