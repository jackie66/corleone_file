package com.corleone.file.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 日期时间格式对象
     */
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 日期格式对象
     */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 时间格式对象
     */
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * yyyyMMddHHmmss  yyyy-MM-dd HH:mm:ss
     * 将Date对象转换为特定格式的字符串，
     * 不建议过多调用这个函数，如果默认的时间格式符合，就调相应的函数
     *
     * @param pattern 转换格式
     * @return 转换后的时间字符串
     */
    public static String getStringWithPattern(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 将日期对象转成日期时间格式的字符串
     *
     * @param date 日期对象
     * @return 日期时间字符串
     */
    public static String getStringDateTime(Date date) {
        return dateTimeFormat.format(date);
    }

    /**
     * 将日期对象转成日期格式的字符串
     *
     * @param date 日期对象
     * @return 日期字符串
     */
    public static String getStringDate(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 将日期对象转成时间格式的字符串
     *
     * @param date 日期对象
     * @return 时间字符串
     */
    public static String getStringTime(Date date) {
        return timeFormat.format(date);
    }

//    /**
//     * 对日期时间字符串解析转成相应的日期对象
//     * <p>
//     * 这个函数会做判定匹配，如果知道特定的格式，请勿调用该函数
//     * 不建议多次调用
//     *
//     * @param dateStr 日期时间字符串
//     * @return 日期对象
//     */
//    public static Date getDate(String dateStr) {
//        try {
//            if (dateStr.matches("\\d{1,4}-\\d{1,2}-\\d{1,2}")) {
//                return dateFormat.parse(dateStr);
//            } else if (dateStr.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
//                return dateTimeFormat.parse(dateStr);
//            } else if (dateStr.matches("\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
//                return timeFormat.parse(dateStr);
//            }
//            return dateTimeFormat.parse(dateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 对日期格式字符串进行解析生成日期对象
     *
     * @param dateStr 日期字符串
     * @return 日期对象
     * @throws ParseException
     */
    public static Date getDate(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对日期时间格式字符串进行解析生成日期时间对象
     *
     * @param dateTimeStr 日期时间字符串
     * @return 日期时间对象
     * @throws ParseException
     */
    public static Date getDateTime(String dateTimeStr) {
        if (StringUtils.isEmpty(dateTimeStr)) {
            return null;
        }
        try {
            return dateTimeFormat.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对时间格式字符串进行解析生成时间对象
     *
     * @param timeStr 时间字符串
     * @return 时间对象
     * @throws ParseException
     */
    public static Date getTime(String timeStr) {
        if (StringUtils.isEmpty(timeStr)) {
            return null;
        }
        try {
            return timeFormat.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前时间days天后的时间
     *
     * @param days 控制往后的天数,
     * @return 时间对象
     */
    public static Date getDateAfter(Integer days) {
        if (days == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 获取两个日期之间相差的天数
     *
     * @param startDay 开始日期
     * @param endDay   结束日期
     * @return 相差的天数
     */
    public static Integer getDaysBetween(Date startDay, Date endDay) {
        long dayMillis = endDay.getTime() - startDay.getTime();
        Long days = dayMillis / (1000 * 3600 * 24);
        return days.intValue();
    }

}