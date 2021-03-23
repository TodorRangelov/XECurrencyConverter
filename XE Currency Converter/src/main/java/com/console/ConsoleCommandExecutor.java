package com.console;

import com.domain.commands.ConvertCommand;
import com.domain.commands.EndCommand;
import com.domain.entities.dtos.UserRegisterDto;
import com.domain.io.Logger;
import com.external.CurrConvAPI;
import com.service.MoneyServiceImpl;
import com.console.helper.ExchangePair;
import com.repository.ExchangeCacheMemoryImpl;
import com.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ConsoleCommandExecutor {

    private CurrConvAPI currConvExchangeService;
    private Logger logger;
    private MoneyServiceImpl exchangeMoney;
    private ExchangeCacheMemoryImpl cacheMemory;

    @Autowired
    public ConsoleCommandExecutor(CurrConvAPI currConvExchangeService,
                                  Logger logger,
                                  MoneyServiceImpl exchangeMoney,
                                  ExchangeCacheMemoryImpl cacheMemory) {
        this.currConvExchangeService = currConvExchangeService;
        this.logger = logger;
        this.exchangeMoney = exchangeMoney;
        this.cacheMemory = cacheMemory;
    }

    public void execute(String command, ExchangePair exchangePair) {

        switch (command) {

            case "END":

                new EndCommand().execute();
                break;

            case "CONVERT":

                new ConvertCommand(
                        exchangePair,
                        logger,
                        currConvExchangeService,
                        // Such a param should be of type ExchangeService or ExchangeMoney?
                        exchangeMoney,
                        cacheMemory
                        ).execute();
                break;

        }
    }

}
