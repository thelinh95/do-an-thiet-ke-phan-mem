package com.baouyen.doan.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static com.baouyen.doan.entity.Voucher.EXPIRED_DAYS;

public class DateTimeUtil {
    public static String localDateToString(LocalDate localDate, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }

    public static LocalDate stringToDate(String date, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, formatter);
    }

    /**
     * Voucher expired time is 7 days.
     * @return
     */
    public static Long getExpiredTimeEpoch() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime futureTime = currentTime.plusDays(EXPIRED_DAYS);
        Instant instant = futureTime.toInstant(ZoneOffset.UTC);
        return instant.toEpochMilli();
    }

    public static Long getCurrentEpochTime(){
        return Instant.now().toEpochMilli();
    }
}
