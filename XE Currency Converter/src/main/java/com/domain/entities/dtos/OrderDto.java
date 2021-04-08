package com.domain.entities.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderDto {

    private String toCurrency;
    private String fromCurrency;
    private BigDecimal currencyRate;
    private String date;

}
