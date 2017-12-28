package com.wan.grace.dateswitcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 开发部 on 2017/12/28.
 */

public abstract class DateConvertUtils {

    public static final long DAY_TO_LONG = 1000 * 60 * 60 * 24;

    public static String getNowDay(long date) {
        String dateStr = "";
        dateStr = longToString(date, "yyyy年MM月dd日");
        return dateStr;
    }

    public static String nextDay(long date) {
        String dateStr = "";
        date = date + DAY_TO_LONG;
        dateStr = longToString(date, "yyyy年MM月dd日");
        return dateStr;
    }

    public static String beforeDay(long date) {
        String dateStr = "";
        date = date - DAY_TO_LONG;
        dateStr = longToString(date, "yyyy年MM月dd日");
        return dateStr;
    }

    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        return week;
    }

    public static String getWeek(long date,String formatType) {
        Date curDate = longToDate(date,formatType);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(curDate);
        return week;
    }

    public static String longToString(long currentTime, String formatType) {
        String strTime = "";
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType) {
        Date date = null;
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatType);
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType) {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }
}
