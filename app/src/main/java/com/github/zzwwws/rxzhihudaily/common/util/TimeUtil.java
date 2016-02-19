package com.github.zzwwws.rxzhihudaily.common.util;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {

    //一小时的微秒数
    public static final int HOUR_IN_MS = 3600 * 1000;

    //一天的微秒数
    public static final int DAY_IN_MS = 24 * HOUR_IN_MS;

    //一周的微秒数
    public static final int WEEK_IN_MS = 7 * DAY_IN_MS;

    public static final SimpleDateFormat YMD_SDF = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final SimpleDateFormat MD_SDF = new SimpleDateFormat("MM-dd", Locale.getDefault());
    public static final SimpleDateFormat TIME_12_SDF = new SimpleDateFormat("hh:mm", Locale.getDefault());
    public static final SimpleDateFormat TIME_24_SDF = new SimpleDateFormat("HH:mm", Locale.getDefault());
    public static final SimpleDateFormat TIME_0_TO_11_SDF = new SimpleDateFormat("KK:mm", Locale.getDefault());
    public static final SimpleDateFormat FULL_DIVIDE_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public static final SimpleDateFormat FULL_SDF = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());


    public static boolean isEarly(int days, long time) {
        return (getNow_millisecond() - time) > (days * 24 * 3600 * 1000);
    }

    public static String getNowString() {
        return Long.toString((new Date()).getTime() / 1000);
    }

    /**
     * 获取当前时间值，单位是秒
     *
     * @return
     */
    public static int getNow() {
        return (int) ((new Date()).getTime() / 1000);
    }

    public static long getNow_millisecond() {
        return (new Date()).getTime();
    }

    public static long[] getTsTimes() {
        long[] times = new long[2];

        Calendar calendar = Calendar.getInstance();

        times[0] = calendar.getTimeInMillis() / 1000;

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        times[1] = calendar.getTimeInMillis() / 1000;

        return times;
    }

    public static int getLsTime() {
        return getNow();
    }

    public static String getNowDatetime() {
        return (FULL_DIVIDE_SDF.format(new Date()));
    }

    public static String getNowDateTime(String format) {
        Date date = new Date();

        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        return df.format(date);
    }

    public static String getFullTimeString(long milliseconds) {
        Date date = new Date(milliseconds);
        return FULL_SDF.format(date);
    }

    public static String getNowDatetimeSimply(){
        return FULL_SDF.format(new Date()).substring(0, 8);
    }

    public static String getPastDatetimeString(int day){
        long mis = getNow_millisecond() - day * 24 * 3600 * 1000;
        return FULL_SDF.format(new Date(mis)).substring(0, 8);
    }

    public static String getPastDateStringDisplay(int day){
        long mis = getNow_millisecond() - day * 24 * 3600 * 1000;
        String fully =  FULL_SDF.format(new Date(mis));
        String mmdd = fully.substring(4,6)+"月"+fully.substring(6,8)+"日";
        String weekDay = getWeekOfDate(new Date(mis));
        return mmdd +" "+weekDay;
    }
    public static String getBeijingNowTime(String format) {
        TimeZone timezone = TimeZone.getTimeZone("Asia/Shanghai");

        Date date = new Date(getNow_millisecond());
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        formatter.setTimeZone(timezone);

        return formatter.format(date);
    }

    public static String getDateTimeString(long milliseconds, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        return getDateTimeString(milliseconds, formatter);
    }

    public static String getDateTimeString(long milliseconds, SimpleDateFormat formatter) {
        Date date = new Date(milliseconds);
        return formatter.format(date);
    }


    public static String getFavoriteCollectTime(long milliseconds) {
        String showDataString = "";
        Date today = new Date();
        Date date = new Date(milliseconds);
        Date firstDateThisYear = new Date(today.getYear(), 0, 0);
        if (!date.before(firstDateThisYear)) {
            showDataString = MD_SDF.format(date);
        } else {
            showDataString = YMD_SDF.format(date);
        }
        return showDataString;
    }

    public enum Style {
        SHORT, LONG, ADAPTIVE
    }

    public static String getTimeShowString(long milliseconds, Style style) {
        String dataString = "";
        String timeStringBy24 = "";

        Date currentTime = new Date(milliseconds);
        Date today = new Date();
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        Date todaybegin = todayStart.getTime();
        Date yesterdaybegin = new Date(todaybegin.getTime() - 3600 * 24 * 1000);
        Date preyesterday = new Date(yesterdaybegin.getTime() - 3600 * 24 * 1000);

        boolean containZH = true;
        if (!currentTime.before(todaybegin)) {
            dataString = "今天";
        } else if (!currentTime.before(yesterdaybegin)) {
            dataString = "昨天";
        } else if (!currentTime.before(preyesterday)) {
            dataString = "前天";
        } else if (isSameWeekDates(currentTime, today)) {
            dataString = getWeekOfDate(currentTime);
        } else {
            dataString = YMD_SDF.format(currentTime);
            containZH = false;
        }

        timeStringBy24 = TIME_24_SDF.format(currentTime);

        switch (style) {
            case SHORT:
                if (!currentTime.before(todaybegin)) {
                    return getTodayTimeBucket(currentTime);
                } else {
                    return dataString;
                }
            case LONG:
                return dataString + " " + timeStringBy24;
            case ADAPTIVE:
                if (containZH) {
                    return dataString + " " + timeStringBy24;
                } else {
                    return dataString;
                }
            default:
                return dataString;

        }
    }


    /**
     * 根据不同时间段，显示不同时间
     *
     * @param date
     * @return
     */
    public static String getTodayTimeBucket(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 0 && hour < 5) {
            return "凌晨 " + TIME_0_TO_11_SDF.format(date);
        } else if (hour >= 5 && hour < 12) {
            return "上午 " + TIME_0_TO_11_SDF.format(date);
        } else if (hour >= 12 && hour < 18) {
            return "下午 " + TIME_12_SDF.format(date);
        } else if (hour >= 18 && hour < 24) {
            return "晚上 " + TIME_12_SDF.format(date);
        }
        return "";
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        // String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    public static boolean isSameDay(long time1, long time2) {
        return isSameDay(new Date(time1), new Date(time2));
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) ==
                cal2.get(Calendar.DAY_OF_YEAR);
        return sameDay;
    }

    /**
     * 判断两个日期是否在同一周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    public static long getSecondsByMilliseconds(long milliseconds) {
        long seconds = new BigDecimal((float) ((float) milliseconds / (float) 1000)).setScale(0, BigDecimal
                .ROUND_HALF_UP).intValue();
        // if (seconds == 0) {
        // seconds = 1;
        // }
        return seconds;
    }

    public static long getSecondsByMilliseconds0(long milliseconds) {
        long seconds = getSecondsByMilliseconds(milliseconds);
        if (seconds == 0) {
            seconds = 1;
        }
        return seconds;
    }

    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static String getElapseTimeForShow(int milliseconds) {
        StringBuilder sb = new StringBuilder();
        int seconds = milliseconds / 1000;
        if (seconds < 1)
            seconds = 1;
        int hour = seconds / (60 * 60);
        if (hour != 0) {
            sb.append(hour).append("小时");
        }
        int minute = (seconds - 60 * 60 * hour) / 60;
        if (minute != 0) {
            sb.append(minute).append("分");
        }
        int second = (seconds - 60 * 60 * hour - 60 * minute);
        if (second != 0) {
            sb.append(second).append("秒");
        }
        return sb.toString();
    }

    public static String getElapseHourForShow(int milliseconds) {
        StringBuilder sb = new StringBuilder();
        int seconds = milliseconds / 1000;
        if (seconds < 1)
            seconds = 1;
        int hour = seconds / (60 * 60);
        if (seconds % (60 * 60) != 0) {
            hour += 1;
        }

        sb.append(hour).append("小时");

        return sb.toString();
    }

    /**
     * 判断当前时间范围，如夜间模式时判断是否在夜间
     *
     * @param hour1 起始时间，例如23.5f，为23:30
     * @param hour2 结束时间，例如6.0f，为6:00，如果hour2 < hour1，hour2为第二天6点
     */
    public static boolean isBetween(float hour1, float hour2) {
        if (HOUR_IN_MS < 1000 * 3600)
            return true;

        if (hour2 < hour1)
            hour2 += 24;
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        long baseTime = todayStart.getTime().getTime();
        long start = baseTime + Math.round(hour1 * HOUR_IN_MS);
        long end = baseTime + Math.round(hour2 * HOUR_IN_MS);
        long current = System.currentTimeMillis();
        if (current > start && current < end)
            return true;
        else
            return false;
    }

    /**
     * 生日转换为年龄
     *
     * @param birth
     * @return
     */
    public static String birthdayToAge(String birth) {
        if (TextUtils.isEmpty(birth))
            return "";

        Date curDate = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        try {
            calendar.setTime(curDate);
            int curYear = calendar.get(Calendar.YEAR);
            calendar.setTime(YMD_SDF.parse(birth));
            int birthYear = calendar.get(Calendar.YEAR);
            return String.valueOf(curYear - birthYear);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 根据initDate来初始化initCalendar，若initDate为空
     * 则initCalendar初始化为当前时间－25年
     * @param initCalendar
     * @param initDate
     * @return
     */
    public static Calendar initCalendar(Calendar initCalendar, String initDate) {
        try {
            if (initCalendar == null) {
                initCalendar = Calendar.getInstance();
                initCalendar.setTime(new Date());
                initCalendar.add(Calendar.YEAR, -25);
            }
            if (!TextUtils.isEmpty(initDate)) {
                initCalendar.setTime(YMD_SDF.parse(initDate));
            }
            return initCalendar;
        } catch (Exception e) {
            return initCalendar;
        }
    }
}
