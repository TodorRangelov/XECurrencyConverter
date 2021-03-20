package com.controllers;

import com.console.ConsoleRunner;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {

    private final ConsoleRunner consoleRunner;
    private final UserService userService;

    @Autowired
    public AppController(ConsoleRunner consoleRunner, UserService userService) {
        this.consoleRunner = consoleRunner;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        consoleRunner.run();
    }
}
