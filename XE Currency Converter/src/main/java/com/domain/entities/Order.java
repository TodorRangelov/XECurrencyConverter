package com.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "to_currency", nullable = false)
    private String toCurrency;

    @Column(name = "from_currency", nullable = false)
    private String fromCurrency;

    @Column(name = "currency_rate", nullable = false)
    private BigDecimal currencyRate;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    public Order() {
    }

}
