package com.geekerstar.basic.java8.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author geekerstar
 * @date 2022/3/8 18:24
 */
public class ConvertTest {

    /**
     * LocalDateTime to String(将LocalDateTime 转为自定义的时间格式字符串
     *
     * @param localDateTime
     * @param format
     * @return
     */
    public static String localDateTimeToString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    /**
     * timestamp to LocalDateTime (将long类型的timeStamp转为LocalDateTime )
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime timestampToLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * LocalDateTime to timestamp ( 将LocalDateTime转为long类型的timeStamp )
     *
     * @param localDateTime
     * @return
     */
    public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * LocalDateTime to Date ( 将LocalDateTime to Date )
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * String to 自定义格式 ( Formatter ) 的LocalDateTime ( 将时间字符串转为自定义格式的LocalDateTime )
     *
     * @param time
     * @param format
     * @return
     */
    public static LocalDateTime parseStringToDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }

    /**
     * Date to LocalDateTime ( 将Date转为LocalDateTime )
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * Date to LocalDate（ 将Date 转为 LocalDate ）
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    /**
     * LocalDate to Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * Date to LocalTime ( 将 Date 转为 LocalTime )
     *
     * @param date
     * @return
     */
    public static LocalTime dateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalTime();
    }

    /**
     * LocalTime to Date
     *
     * @param localTime
     * @param localDate
     * @return
     */
    public static Date localTimeToDate(LocalTime localTime, LocalDate localDate) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

}
