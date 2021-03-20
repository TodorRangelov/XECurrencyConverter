package repository;

import com.domain.entities.Money;
import com.console.helper.ExchangePair;
import com.repository.ExchangeCacheMemoryImpl;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ExchangeCacheMemoryTest {

    @Test
    public void isMemoryExpiredReturnTrueWhenCalledForFirstTime() {

        ExchangeCacheMemoryImpl cacheMemory = new ExchangeCacheMemoryImpl();
        ExchangePair exchangePair = new ExchangePair(new Money(new BigDecimal(1), "BGN"), "USD");


        Assert.assertTrue(cacheMemory.isMemoryExpired(exchangePair));
    }

    @Test
    public void isMemoryExpiredReturnsTrue_WhenMemoryHasExpired() throws InterruptedException {
        ExchangeCacheMemoryImpl cacheMemory = new ExchangeCacheMemoryImpl();
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

        ExchangeCacheMemoryImpl cacheMemory = new ExchangeCacheMemoryImpl();
        ExchangePair exchangePair = new ExchangePair(new Money(new BigDecimal(1), "BGN"), "USD");

        cacheMemory.putValue(exchangePair, "1");


        Assert.assertFalse(cacheMemory.isMemoryExpired(exchangePair));
    }


}