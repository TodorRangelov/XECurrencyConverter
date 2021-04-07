package com.repository;

import com.domain.entities.Order;
import com.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Override
    Optional<Order> findById(Integer integer);

    Optional<Order> findByCurrencyRateIsAfter(BigDecimal rate);

    Optional<Order> findByCurrencyRateIsBefore(BigDecimal rate);

    Optional<Order> findByCurrencyRateGreaterThanEqual(BigDecimal rate);

    Optional<Order> findByDateAfter(LocalDate date);

    Optional<Order> findByFromCurrency(String fromCurrency);

    Optional<Order> findByFromCurrencyEquals(String currency);

    Optional<Order> findByUser(User user);

    Optional<Order> findByUserId(Integer id);

    Set<Order> findAllByUserId(Integer userId);
}
