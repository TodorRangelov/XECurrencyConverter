package domain.extenal;

import domain.entities.Money;

public interface ExchangeService {

    public Money exchange(Money from, String toCurrency);
}
