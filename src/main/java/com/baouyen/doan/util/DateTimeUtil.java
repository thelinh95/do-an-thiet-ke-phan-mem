package com.baouyen.doan.util;

import java.time.LocalDate;
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
}
