package domain.external;

import domain.entities.Money;
import domain.io.Logger;
import external.CurrConvAPI;
import helper.ExchangePair;

public interface ExchangeService {

    Money exchange(ExchangePair exchangePair,
                   CurrConvAPI currConv,
                   Logger logger);

}
