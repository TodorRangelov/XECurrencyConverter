package controllers;

import console.ConsoleRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {

    private final ConsoleRunner consoleRunner;

    @Autowired
    public AppController(ConsoleRunner consoleRunner) {
        this.consoleRunner = consoleRunner;
    }

    @Override
    public void run(String... args) throws Exception {

        consoleRunner.run();
    }
}
