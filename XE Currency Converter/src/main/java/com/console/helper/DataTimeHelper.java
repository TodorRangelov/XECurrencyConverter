package com.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataTimeHelper {

    public static String getCurrentTimeAsString() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        return now.format(format);
    }

}
