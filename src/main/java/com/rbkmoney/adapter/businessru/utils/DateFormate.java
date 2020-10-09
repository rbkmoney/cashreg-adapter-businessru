package com.rbkmoney.adapter.businessru.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormate {

    public static String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }

}
