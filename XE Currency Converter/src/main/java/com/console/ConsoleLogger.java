package com.console;

import com.domain.entities.dtos.OrderDto;
import com.domain.entities.dtos.UserLogDto;
import com.domain.io.Logger;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConsoleLogger implements Logger {

    public ConsoleLogger() {
    }

    @Override
    public void logLine(String line) {
        System.out.println(line);
    }

    public void logSetString(Set<String> set) {
        set.forEach(System.out::println);
    }

    public void logUserLogDto(UserLogDto user) {
        System.out.printf("Successfully logged in %s%n", user.getName());
    }

    public void logAllUsersOrders(Set<OrderDto> orders) {
        System.out.println();
        orders.forEach(o -> System.out.printf("Rate %s From Currency %s To Currency %s Date %s%n",
                o.getCurrencyRate(),
                o.getFromCurrency(),
                o.getToCurrency(),
                o.getDate()));
    }
}
