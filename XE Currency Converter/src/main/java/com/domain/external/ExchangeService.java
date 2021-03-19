package domain.external;

import domain.entities.Money;
import domain.io.Logger;
import external.CurrConvAPI;
import helper.ExchangePair;
import repository.ExchangeCacheMemory;

public interface ExchangeService {

    Money exchange(ExchangePair exchangePair,
                   CurrConvAPI currConv,
                   Logger logger,
                   ExchangeCacheMemory cacheMemory);
}
