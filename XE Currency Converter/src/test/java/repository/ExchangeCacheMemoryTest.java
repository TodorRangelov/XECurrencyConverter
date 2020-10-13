package repository;

import domain.entities.Money;
import helper.ExchangePair;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ExchangeCacheMemoryTest {

    @Test
    public void isMemoryExpiredReturnTrueWhenCalledForFirstTime() {

        ExchangeCacheMemory cacheMemory = new ExchangeCacheMemory();
        ExchangePair exchangePair = new ExchangePair(new Money(new BigDecimal(1), "BGN"), "USD");


        Assert.assertTrue(cacheMemory.isMemoryExpired(exchangePair));
    }

    @Test
    public void isMemoryExpiredReturnsTrue_WhenMemoryHasExpired() throws InterruptedException {
        ExchangeCacheMemory cacheMemory = new ExchangeCacheMemory();
        ExchangePair exchangePair = new ExchangePair(new Money(new BigDecimal(1), "BGN"), "USD");

        cacheMemory.putValue(exchangePair, "1");

        // Given: memory duration is 1 sec
        cacheMemory.setDurationOfValidityInSeconds(1);

        // When: 2 seconds passed
        Thread.sleep(2000);

        // Then: Memory has expired
        Assert.assertTrue(cacheMemory.isMemoryExpired(exchangePair));
    }

    @Test
    public void isMemoryExpiredReturnFalseWhenMemoryIsValid() {

        ExchangeCacheMemory cacheMemory = new ExchangeCacheMemory();
        ExchangePair exchangePair = new ExchangePair(new Money(new BigDecimal(1), "BGN"), "USD");

        cacheMemory.putValue(exchangePair, "1");


        Assert.assertFalse(cacheMemory.isMemoryExpired(exchangePair));
    }


}