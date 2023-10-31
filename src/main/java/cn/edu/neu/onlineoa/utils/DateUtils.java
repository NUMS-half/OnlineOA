package cn.edu.neu.onlineoa.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    /**
     * return the LocalDateTime with certain format
     * @param format
     * @return now.format(formatter)
     */
    public static String getLocalDateTime(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return now.format(formatter);
    }
}
