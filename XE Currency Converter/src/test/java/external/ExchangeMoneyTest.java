package external;

import console.ConsoleLogger;
import domain.entities.Money;
import domain.io.Logger;
import helper.ExchangePair;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import repository.ExchangeCacheMemory;

import java.math.BigDecimal;

public class ExchangeMoneyTest {

    @Test
    public void convertingBGNtoEURWithRate2x() {

        Logger logger = new ConsoleLogger();
        ExchangeCacheMemory mockitoMemory = Mockito.mock(ExchangeCacheMemory.class);

        ExchangePair exchangePair = new ExchangePair(new Money(new BigDecimal("1.5"), "BGN"), "EUR");
        Mockito.when(mockitoMemory.isMemoryExpired(exchangePair)).thenReturn(true);

        CurrConvAPI mockitoCurrConvAPI = Mockito.mock(CurrConvAPI.class);
        Mockito.when(mockitoCurrConvAPI.fetchExchangeRateFor(exchangePair)).thenReturn("2");

        ExchangeMoney exchangeMoney = new ExchangeMoney();
        ExchangeCacheMemory cacheMemory = new ExchangeCacheMemory();

        Money exchange = exchangeMoney.exchange(exchangePair, mockitoCurrConvAPI, logger, cacheMemory);


        Assert.assertEquals(new BigDecimal("3.0"), exchange.getValue());
    }

    @Test
    public void convertingBGNtoEURWithRate3x() {

        Logger logger = new ConsoleLogger();
        ExchangeCacheMemory mockitoMemory = Mockito.mock(ExchangeCacheMemory.class);

        ExchangePair exchangePair = new ExchangePair(new Money(new BigDecimal("1.5"), "BGN"), "EUR");
        Mockito.when(mockitoMemory.isMemoryExpired(exchangePair)).thenReturn(true);

        CurrConvAPI mockitoCurrConvAPI = Mockito.mock(CurrConvAPI.class);
        Mockito.when(mockitoCurrConvAPI.fetchExchangeRateFor(exchangePair)).thenReturn("3");

        ExchangeMoney exchangeMoney = new ExchangeMoney();
        ExchangeCacheMemory cacheMemory = new ExchangeCacheMemory();

        Money exchange = exchangeMoney.exchange(exchangePair, mockitoCurrConvAPI, logger, cacheMemory);


        Assert.assertEquals(new BigDecimal("4.5"), exchange.getValue());
    }


}