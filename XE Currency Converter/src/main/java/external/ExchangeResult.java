package external;

import java.math.BigDecimal;

public class ExchangeResult {

    BigDecimal result;
    boolean success;
    String errorMessage;

    public ExchangeResult(BigDecimal result, boolean success, String errorMessage) {
        this.result = result;
        this.success = success;
        this.errorMessage = errorMessage;
    }
}
