package com.console;

import com.domain.commands.ConvertCommand;
import com.domain.commands.EndCommand;
import com.domain.entities.User;
import com.domain.entities.dtos.OrderDto;
import com.domain.io.Logger;
import com.external.CurrConvAPI;
import com.service.MoneyServiceImpl;
import com.console.helper.ExchangePair;
import com.repository.ExchangeCacheMemoryImpl;
import com.service.OrderService;
import com.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Getter
@Setter
public class ConsoleCommandExecutor {

    private CurrConvAPI currConvExchangeService;
    private Logger logger;
    private MoneyServiceImpl exchangeMoney;
    private ExchangeCacheMemoryImpl cacheMemory;
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public ConsoleCommandExecutor(CurrConvAPI currConvExchangeService,
                                  Logger logger,
                                  MoneyServiceImpl exchangeMoney,
                                  ExchangeCacheMemoryImpl cacheMemory, UserService userService, OrderService orderService) {
        this.currConvExchangeService = currConvExchangeService;
        this.logger = logger;
        this.exchangeMoney = exchangeMoney;
        this.cacheMemory = cacheMemory;
        this.userService = userService;
        this.orderService = orderService;
    }

    public void execute(String command, ExchangePair exchangePair) {

        switch (command) {

            case "END":

                new EndCommand().execute();
                break;

            case "CONVERT":

                List<String> rateAndMoney = new ConvertCommand(
                        exchangePair,
                        logger,
                        currConvExchangeService,
                        exchangeMoney,
                        cacheMemory
                ).execute();

                System.out.printf("%s%n%s", rateAndMoney.get(1), rateAndMoney.get(2));

                OrderDto orderDto = new OrderDto();

                orderDto.setCurrencyRate(new BigDecimal(rateAndMoney.get(0)));

                String toCurrencyValue = String.format("%s %s", exchangePair.getToCurrency(), rateAndMoney.get(3));

                orderDto.setFromCurrency(exchangePair.getFromCurrency() + " " + exchangePair.getFromMoney().getValue());
                orderDto.setToCurrency(toCurrencyValue);

                User user = userService.getLoginUser();

                orderService.addOrder(orderDto, user);
                break;

        }
    }

}
