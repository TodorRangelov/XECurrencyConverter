package com.service;

import com.domain.entities.Money;
import com.domain.io.Logger;
import com.external.CurrConvAPI;
import com.console.helper.ExchangePair;
import com.repository.ExchangeCacheMemoryImpl;

public interface MoneyService {

    Money exchange(ExchangePair exchangePair,
                   CurrConvAPI currConv,
                   Logger logger,
                   ExchangeCacheMemoryImpl cacheMemory);
}
