package com.bootdo.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 */
public class DateUtils {
    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_FORMAT = "yyyyMMddHHmmssSSS";
    public static final String DEFAULT_FORMAT_STRING = "yyyyMMddHHmmss";
    public static final String DEFAULT_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String DEFAULT_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DEFAULT_YEAR_MON_DAY = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_YEAR_MON_DAY2 = "yyyy/MM/dd HH:mm:ss";
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        } else if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * long to str
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String formatLongToStr(long time, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DEFAULT_YEAR_MON_DAY;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(time);
        String sDateTime = sdf.format(date);  //得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }

    /**
     * long to str
     *
     * @param time
     * @return
     */
    public static String formatLongToStr(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_YEAR_MON_DAY);
        Date date = new Date(time);
        String sDateTime = sdf.format(date);  //得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }

    /**
     * 比较大小
     *
     * @param date1
     * @param nowDate
     * @return
     */
    public static int compareDate(String date1, String nowDate) {

        DateFormat df = new SimpleDateFormat(DEFAULT_YEAR_MON_DAY);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(nowDate);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    public static int differentDays(long time1, long nowtime) {
        Date date = new Date(time1);
        Date date1 = new Date(nowtime);
        int days = differentDays(date, date1);

        return days;
    }

    public static void main(String args[]) {
        int i = compareDate("2000-11-12 15:21:00", "1999-12-11 09:59:00");
        System.out.println("i==" + i);

        System.out.println(differentDays(Long.valueOf("1522772612655"), System.currentTimeMillis()));
    }

}
