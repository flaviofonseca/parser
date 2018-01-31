package com.ef.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilDate {

    public static LocalDateTime converteStringToLocalDateTime(String localDate) {
        return UtilDate.converteStringToLocalDateTime(localDate, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static LocalDateTime converteStringToLocalDateTime(String localDate, String formatter) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        LocalDateTime localDateTime = LocalDateTime.parse(localDate, dateTimeFormatter);
        return localDateTime;
    }

    public static LocalDateTime addHourToDate(LocalDateTime startDate) {
        return startDate.plusHours(1);
    }

    public static LocalDateTime addDayToDate(LocalDateTime startDate) {
        return startDate.plusDays(1);
    }
}
