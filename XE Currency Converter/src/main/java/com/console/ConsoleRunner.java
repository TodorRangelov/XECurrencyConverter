package com.console;

import com.domain.entities.dtos.OrderDto;
import com.domain.entities.dtos.UserRegisterDto;
import com.external.CurrConvAPI;
import com.service.MoneyServiceImpl;
import com.console.helper.ExchangePair;
import com.console.helper.ParserCommand;
import com.helper.checkInput.*;
import com.console.helper.UserMessagesHelper;
import com.repository.ExchangeCacheMemoryImpl;
import com.service.OrderService;
import com.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
@Getter
@Setter
public class ConsoleRunner {

    private Scanner scanner = new Scanner(System.in);
    private ConsoleLogger consoleLogger;
    private ConsoleReader reader;
    private UserMessagesHelper messages;
    private CurrConvAPI currConvAPI;
    private MoneyServiceImpl exchangeMoney;
    private ExchangeCacheMemoryImpl cacheMemory;
    private final UserService userService;
    private final OrderService orderService;
    private ExchangePair exchangePair;
    private ConsoleCommandExecutor commandExecutor;
    private List<String> args = new ArrayList<>();

    @Autowired
    public ConsoleRunner(
            ConsoleLogger consoleLogger,
            ConsoleReader reader,
            UserMessagesHelper messages,
            CurrConvAPI currConvAPI,
            MoneyServiceImpl exchangeMoney,
            ExchangeCacheMemoryImpl cacheMemory,
            ConsoleCommandExecutor commandExecutor,
            ExchangePair exchangePair,
            UserService userService, OrderService orderService) {
        this.consoleLogger = consoleLogger;
        this.reader = reader;
        this.messages = messages;
        this.currConvAPI = currConvAPI;
        this.exchangeMoney = exchangeMoney;
        this.cacheMemory = cacheMemory;
        this.commandExecutor = commandExecutor;
        this.exchangePair = exchangePair;
        this.userService = userService;
        this.orderService = orderService;
    }

    public void run() {


        while (true) {

            if (isThereLoggedUser()) {

                args.add(reader.readCommandRegisterLoginOrEndAndCheck(
                        scanner,
                        consoleLogger,
                        messages.selectCommandRegisterLoginOrEnd(),
                        new IncorrectCommands()));

                if (args.get(0).equals("REGISTER")) {
                    this.registerUser();

                    continue;
                }

                if (args.get(0).equals("LOGIN")) {
                    this.login();
                }

                // login admin user
                if (args.get(0).equals("TEST")) {
                    this.test();
                }
            }

            args.add(reader.readCommandConvertLogoutOrEndAndCheck(
                    scanner,
                    consoleLogger,
                    messages.selectCommand(),
                    new IncorrectCommands()));

            ParserCommand parser = new ParserCommand(args);

            if (args.get(0).equals("END")) {
                args.set(0, "END");
                commandExecutor.execute(parser.getCommand().toString(), exchangePair);
            }

            if (args.get(0).equals("CONVERT")) {
                this.convert(parser);
            }

            if (args.get(0).equals("HISTORY")) {
                this.getAllUsersOrders();

                continue;
            }

            try {
                commandExecutor.execute(parser.getCommand().toString(), exchangePair);
            } catch (Exception e) {
                consoleLogger.logLine("something went wrong during HTTP request to external API");
            }

            if (args.get(0).equals("LOGOUT")) {

                System.out.println(userService.logoutUser());
            }
        }
    }

    private void getAllUsersOrders() {

        Integer logUserId = userService.getUserLogDto().getId();

        Set<OrderDto> allUsersOrders = orderService.getAllUsersOrders(logUserId);

        consoleLogger.logAllUsersOrders(allUsersOrders);
    }

    private boolean isThereLoggedUser() {

        if (userService.getUserLogDto() == null) {
            return true;
        }

        return false;
    }

    private void convert(ParserCommand parser) {
        args.set(0, "CONVERT");

        args.add(reader.readCommandRegisterLoginOrEndAndCheck(
                scanner,
                consoleLogger,
                messages.value(),
                new IncorrectValue()));

        args.add(reader.readCommandRegisterLoginOrEndAndCheck(
                scanner,
                consoleLogger,
                messages.selectCurrencyConvert(),
                new IncorrectCurrency()));

        args.add(reader.readCommandRegisterLoginOrEndAndCheck(
                scanner,
                consoleLogger,
                messages.selectToCurrency(),
                new IncorrectCurrency()));

        parser = new ParserCommand(args);
        exchangePair = parser.getExchangePair(args);
    }

    private void registerUser() {
        UserRegisterDto userRegisterDto = new UserRegisterDto();

        System.out.println("Enter Name:");
        userRegisterDto.setName(scanner.nextLine());

        System.out.println("Enter Email:");
        userRegisterDto.setEmail(scanner.nextLine());

        System.out.println("Enter Password:");
        userRegisterDto.setPassword(scanner.nextLine());

        System.out.println("Confirm Password:");
        userRegisterDto.setConfirmPassword(scanner.nextLine());

        System.out.println(userService.registerUser(userRegisterDto));

        args.remove(0);
    }

    private void login() {

        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        System.out.println(userService.loginUser(email, password));

        userService.loginUser(email, password);

        printLogUser();

        args.remove(0);
    }

    private void test() {

        userService.loginUser("asd@abv.bg", "asdA1asd");
        args.remove(0);
        printLogUser();
    }

    private void printLogUser() {
        consoleLogger.logUserLogDto(userService.getUserLogDto());
    }

}
