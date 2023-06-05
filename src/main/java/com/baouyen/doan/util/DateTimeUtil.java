package com.baouyen.doan.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static String localDateToString(LocalDate localDate, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }

    public static LocalDate stringToDate(String date, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, formatter);
    }

    public static Long getCurrentEpochTime(){
        return Instant.now().toEpochMilli();
    }

    public static Long getEndDateEpoch(LocalDate inputDate){
        LocalDateTime endDateTime = LocalDateTime.of(inputDate, LocalTime.MAX);
        return endDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();

    }
}
