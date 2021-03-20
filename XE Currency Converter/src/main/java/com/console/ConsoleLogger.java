package com.console;

import com.domain.io.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConsoleLogger implements Logger {

    public ConsoleLogger() {
    }

    @Override
    public void logLine(String line) {
        System.out.println(line);
    }
}
