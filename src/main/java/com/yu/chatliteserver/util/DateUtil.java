package com.yu.chatliteserver.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 日期转字符串
     * @return
     */
    public static String dateFormat(LocalDateTime localDateTime) {
        return formatter.format(localDateTime);
    }

    /**
     * 解析字符串 转换日期
     * @return
     */
    public static LocalDateTime DateParse(String str){
        return LocalDateTime.parse(str,formatter);
    }
}
