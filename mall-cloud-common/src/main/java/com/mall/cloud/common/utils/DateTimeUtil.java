package com.mall.cloud.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>封装Qicloud项目DateTimeUtil类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-14 22:18
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@SuppressWarnings({"ALL", "AlibabaAvoidCallStaticSimpleDateFormat"})
public class DateTimeUtil {
    /**
     * 年-月-日 时:分:秒 显示格式
     */
    // 备注:如果使用大写HH标识使用24小时显示格式,如果使用小写hh就表示使用12小时制格式。
    public static String DATE_TO_STRING_DETAIAL_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年-月-日 显示格式
     */
    public static String DATE_TO_STRING_SHORT_PATTERN = "yyyy-MM-dd";
    /**
     * 月-日 显示格式
     */
    public static String DATE_TO_STRING_MORE_SHORT_PATTERN = "MM-dd";

    /**
     * 年-月-日 显示格式
     */
    public static String DATE_TO_STRING_SHORT_DATE_PATTERN = "HH:mm:ss";

    public static String DATE_TO_STRING_SHORT_DATE_MINUTE = "HH:mm";

    /**
     * 一周的天数
     */
    public static final int WEEK_DAY_AMOUNT = 7;


    /**
     * Date类型转为指定格式的String类型
     *
     * @param source
     * @param pattern
     * @return
     */
    @SuppressWarnings("AlibabaAvoidCallStaticSimpleDateFormat")
    public static String DateToString(Date source, String pattern) {
        if (source != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            //noinspection AlibabaAvoidCallStaticSimpleDateFormat
            return simpleDateFormat.format(source);
        } else {
            return "";
        }

    }


    /**
     * unix（秒级）时间戳转为指定格式的String类型
     * <p>
     * <p>
     * System.currentTimeMillis()获得的是是从1970年1月1日开始所经过的毫秒数
     * unix时间戳:是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数,不考虑闰秒
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String timeStampToString(long source, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(source * 1000);
        return simpleDateFormat.format(date);
    }

    /**
     * unix（毫秒级）时间戳转为指定格式的String类型
     * <p>
     * <p>
     * System.currentTimeMillis()获得的是是从1970年1月1日开始所经过的毫秒数
     * unix时间戳:是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数,不考虑闰秒
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String timeMillisToString(long source, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(source);
        return simpleDateFormat.format(date);
    }

    /**
     * 将日期转换为时间戳(unix时间戳,单位秒)
     *
     * @param date
     * @return
     */
    public static long dateToTimeStamp(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.getTime() / 1000;

    }


    /**
     * 获得当前时间对应的指定格式
     *
     * @param pattern
     * @return
     */
    public static String currentFormatDate(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());

    }

    /**
     * 获得当前时间 年月日 yyyy-MM-dd
     *
     * @param pattern
     * @return
     */
    public static String currentFormatDate2MD() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TO_STRING_SHORT_PATTERN);
        return simpleDateFormat.format(new Date());

    }

    /**
     * 获得当前时间 yyyy-MM-dd HH:mm:ss
     *
     * @param pattern
     * @return
     */
    public static String currentFormatDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TO_STRING_DETAIAL_PATTERN);
        return simpleDateFormat.format(new Date());

    }

    /**
     * 获得当前unix时间戳(单位秒)
     *
     * @return 当前unix时间戳
     */
    public static long currentTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * @param nearly 小时间
     * @param far    大时间
     * @return long    返回类型
     * @throws
     * @author liujunwei
     * @Title: timeDifferencename4Seconds
     * @Description: 获取两个时间的差距时间
     */
    public static long timeDifferencename4Seconds(Date nearly, Date far) {
        long interval = 0;
        try {
            interval = (far.getTime() - nearly.getTime()) / 1000;
//            ("两个时间相差" + interval + "秒");//会打印出相差3秒
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interval;
    }

    /**
     * @throws
     * @Title: timestamp2Date
     * @Description: 时间戳（单位：豪秒）转化为Date
     */
    public static Date timestamp2Date(Long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(timestamp);
        Date date = null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }


    /**
     * @throws
     * @Title: time2ShortDate
     * @Description: 时间戳（单位：豪秒）转化为Date
     */
    public static Date time2ShortDate(Long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TO_STRING_SHORT_PATTERN);
        String d = format.format(timestamp);
        Date date = null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间字符串转换成
     *
     * @param time
     * @return
     */
    public static Date timeStr2ShortDate(String time) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TO_STRING_SHORT_PATTERN);
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转换成短的时间字符串 月日
     *
     * @param time
     * @return
     */
    public static String date2ShortTimeStr(Date time) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TO_STRING_MORE_SHORT_PATTERN);
        String str = "";
        try {
            str = format.format(time);
        } catch (Exception e) {
            str = "";
            e.printStackTrace();

        }
        return str;
    }

    /**
     * 将日期格式的字符串转换为长整型
     *
     * @param date
     * @return
     */
    public static long convert2long(String date) {
        try {
            if (StringUtils.isNotBlank(date)) {
                SimpleDateFormat simpleFormat = new SimpleDateFormat(DATE_TO_STRING_DETAIAL_PATTERN);
                return simpleFormat.parse(date).getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * @param nearly 近时间
     * @param far    远时间
     * @return long    返回类型
     * @throws
     * @author liujunwei
     * @Title: timeDifferencename4Seconds
     * @Description: 获取两个时间的差距时间
     */
    public static int timeDifferencename4String(String nearly, String far) {

        int interval = 0;

        SimpleDateFormat simpleFormat = new SimpleDateFormat(DATE_TO_STRING_DETAIAL_PATTERN);

        long from = 0;
        long to = 0;
        try {
            from = simpleFormat.parse(nearly).getTime();
            to = simpleFormat.parse(far).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return interval;
        }

        interval = (int) ((to - from) / (1000 * 60));


        return interval;
    }


    /**
     * 根据阿拉伯数字判断星期几？ 返回下一个最近的日期是几号？
     * 如果传入的数刚好是今天 还要判断时间是否度过了 如果过了要返回下个星期的日期
     *
     * @param selectWeeks   星期几（星期一：1 ..... 星期天：7 ）
     * @param time          时间（HH:mm:ss 格式的字符串时间）
     * @param protectionMin 保护时间 分钟维度（作用：判断日期时 对当前时间加一段时间保护）
     * @return
     */
    public static Date getDateBySelectWeeks(String selectWeeks, String time, int protectionMin) {

        //外国人 星期天是一周的第一天 所以这里要处理
        int week;
        if ("7".equals(selectWeeks)) {
            week = 1;
        } else {
            week = new Integer(selectWeeks) + 1;
        }

        // 获取当前日期
        Calendar calendar = Calendar.getInstance();

        // 今天星期几，注：此处周日 = 1 ，周一 = 2 。。。。
        int nowWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 获取当前日期时间
        LocalDate now = LocalDate.now();

        int diff = 0;
        // 判断如果已经度过传入的星期数
        if (nowWeek > week) {
            // 就用传入的数减今天的星期数 并且
            diff = week - nowWeek + 7;
        } else if (nowWeek < week) {
            //
            diff = week - nowWeek;
        } else if (nowWeek == week) {
            long start = convert2long(now.toString() + " " + time);
            long milli = System.currentTimeMillis();
            //判断是否需要保护时间
            if (protectionMin > 0) {
                //加了半个小时（毫秒值）保护时间
                milli = milli + 60 * 1000 * protectionMin;
            }

            //如果传入时间小于现在时间 则取下周时间
            if (start <= milli) {
                System.out.println("取下周时间");
                diff = 7;
            } else {
                diff = 0;
            }
        }

        Date date = null;
        //获取下个周几的时间
        LocalDate nextDay = now.plusDays(diff);
        //拼接上传入的时间
        String str = nextDay.toString() + " " + time;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //转换成date类型返回
        LocalDateTime parse = LocalDateTime.parse(str, df);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = parse.atZone(zone).toInstant();
        date = Date.from(instant);

        return date;
    }

    /**
     * 获取今天还剩下多少秒
     *
     * @return
     */
    public static int getTodayRemainingMillis() {
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate
                .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
                .get(Calendar.DATE) + 1, 0, 0, 0);
        return (int) (tommorowDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000;
    }

    /**
     * 获取距离某个时段的结束时间
     *
     * @param date
     * @return
     */
    public static int getSomedayRemainingMillis(Date date) {
        Calendar curDate = Calendar.getInstance();
        curDate.setTime(date);
        Calendar tommorowDate = new GregorianCalendar(curDate
                .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
                .get(Calendar.DATE) + 1, 0, 0, 0);
        return (int) (tommorowDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000;
    }


    public static Date StringToDate(String source, String pattern) {
        if (source != null) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                return simpleDateFormat.parse(source.toString());
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }

    }

    /**
     * 根据给定时间  获取给定时间的下周一
     *
     * @param date
     * @return
     */
    public static Date getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }

    /**
     * 根据给定时间  获取给定时间的上周一
     *
     * @param date
     * @return
     */
    public static Date geLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    /**
     * 根据给定时间  获取给定时间的周一
     *
     * @param date
     * @return
     */
    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 获取当前月的下个月的 第一天
     *
     * @return 获取第一天
     */
    public static Date getNextMonthFirstDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static Date getThisMouthFirstDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }


    /**
     * 获取当前月的下个月的最后一天
     *
     * @return 获取最后一天
     */
    public static Date getNextMonthMaxDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取当月的最后一天
     *
     * @return 获取最后一天
     */
    public static Date getThisMonthMaxDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
//        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 根据给定时间获取星期几
     *
     * @param date 时间
     * @return 星期几
     */
    public static int getDayForWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayForWeek = 0;
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            dayForWeek = 7;
        } else {
            dayForWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 取给定月的天数
     *
     * @param date 时间
     * @return 天数
     */
    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算时间   +加  -减
     *
     * @param date
     * @param amout
     * @return
     */
    public static Date getCalculateDate(Date date, int amout) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        cal.add(Calendar.DATE, amout);
        return cal.getTime();
    }


    /**
     * 将日期格式的字符串转换为长整型
     *
     * @param date
     * @return
     */
    public static long convert2short(String date) {
        try {
            if (StringUtils.isNotBlank(date)) {
                SimpleDateFormat simpleFormat = new SimpleDateFormat(DATE_TO_STRING_SHORT_PATTERN);
                return simpleFormat.parse(date).getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 判断时间是不是今天
     *
     * @param date
     * @return 是返回true，不是返回false
     */
    public static boolean isNow(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);


        //对比的时间
        String day = sf.format(date);

        return day.equals(nowDay);

    }

    /**
     * 判断时间是否大于当前时间
     *
     * @param date
     * @return
     */
    public static boolean isSpend(Date date) {

        long num = timeDifferencename4Seconds(new Date(), date);

        if (num <= 0) {

            return false;
        } else {
            return true;
        }

    }

    /**
     * 描述:获取下一个月的第一天.（时分秒0）
     *
     * @return
     */
    public static Date getPerFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 描述:获取下个月的最后一天.（时分秒0）
     *
     * @return
     */
    public static Date getLastMaxMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 获取字符串时间戳
     */
    public static Long getStrTime(String cc_time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TO_STRING_DETAIAL_PATTERN);
        Date d;
        Long l = 0L;
        try {

            d = sdf.parse(cc_time);
            l = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;

    }


    /**
     * 获得某天最大时间 2017-10-15 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间 2017-10-15 00:00:00
     */

    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date string2Date(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TO_STRING_DETAIAL_PATTERN);
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取某年某月第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //设置日历中月份的第1天
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取某年某月最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getEndDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //设置日历中月份的第1天
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        //格式化日期
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    /**
     * 获取某段时这里写代码片间内的所有日期
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     * 获取某段时这里写代码片间内的所有日期  显示某日 01日 02日
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<String> findDateList4Day(Date dBegin, Date dEnd) {
        List<String> StringList = new ArrayList<String>();
        StringList.add(DateToString(dBegin, "dd"));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);

            StringList.add(DateToString(calBegin.getTime(), "dd") + "日");
        }
        return StringList;
    }

    public static Long getYesterdayTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTimeInMillis();
    }


    /**
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "";
        try {
            dateString = formatter.format(time);
        } catch (Exception e) {

        }
        return dateString;
    }

    /**
     * @return返回字符串格式 yyyy-MM-dd'T'HH:mm:ss.SSS Z （z-0时区）
     */
    public static String getStringDate4UTC2Z(Date time) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("Z"));
        String dateString = "";
        try {
            dateString = formatter.format(time);
        } catch (Exception e) {

        }
        return dateString;
    }


    /**
     * 判断当前时间在不在0-6点
     *
     * @param args
     */

    public static boolean inZeroTimeToSixTime() {
        Date date = new Date();
        //获取今天日期
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_TO_STRING_SHORT_PATTERN);
        SimpleDateFormat fmt = new SimpleDateFormat(DATE_TO_STRING_DETAIAL_PATTERN);
        String today = formatter.format(date);
        String startStr = today + " 00:00:00";
        String endStr = today + " 06:00:00";
        try {
            Date starTime = fmt.parse(startStr);
            Date endTime = fmt.parse(endStr);
            if (date.getTime() > starTime.getTime() && date.getTime() < endTime.getTime()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }


    public static String formatDate(Date date) {
        try {
            if (null != date) {
                SimpleDateFormat simpleFormat = new SimpleDateFormat(DATE_TO_STRING_DETAIAL_PATTERN);
                return simpleFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断是不是今天
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        if (fmt.format(date).toString().equals(fmt.format(new Date()).toString())) {
            //格式化为相同格式
            return true;
        } else {
            return false;
        }


    }

    //获取当天的开始时间
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    //获取昨天的开始时间
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    //获取昨天的结束时间
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    //获取昨天日期
    public static String getYesterdayStr() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date time = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(DATE_TO_STRING_SHORT_PATTERN);
        String d = format.format(time);
        return d;
    }


    /**
     * 获取下一天
     *
     * @param date
     * @return
     */
    public static Date getNextDate(Date date) {
        long addTime = 1;
        //以1为乘以的基数
        addTime *= 1;
        //1天以后，如果是30天以后则这里是30
        addTime *= 24;
        //1天24小时
        addTime *= 60;
        //1天60分钟
        addTime *= 60;
        //1天60秒钟
        addTime *= 1000;
        //1秒=1000毫秒
        //用毫秒数构造新的日期
        Date nextDate = new Date(date.getTime() + addTime);
        return nextDate;
    }


    public static String MORNING = "MORNING";
    public static String AFTERNOON = "AFTERNOON";
    public static String EVENING = "EVENING";
    private static int six = 6;
    private static int twelve = 12;
    private static int seventeen = 17;
    private static int twenty_four = 24;

    /**
     * 根据时间判断 上午下午晚上
     *
     * @param date
     * @return
     */
    public static String getDuringDay(Date date) {
        int hour = getHour(date);
        if (hour >= six && hour < twelve) {
            return MORNING;
        }
        if (hour >= twelve && hour < seventeen) {
            return AFTERNOON;
        }
        if (hour >= seventeen && hour <= twenty_four) {
            return EVENING;
        }
        return null;
    }

    /**
     * 功能描述：返回小
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回时分秒
     *
     * @param date
     * @return
     */

    public static String formatHourDate(Date date) {
        try {
            if (null != date) {
                SimpleDateFormat simpleFormat = new SimpleDateFormat(DATE_TO_STRING_SHORT_DATE_PATTERN);
                return simpleFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatMinuteDate(Date date) {
        try {
            if (null != date) {
                SimpleDateFormat simpleFormat = new SimpleDateFormat(DATE_TO_STRING_SHORT_DATE_MINUTE);
                return simpleFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param startTime
     * @param endTime
     * @return 0 未开始 2 已结束 1直播中
     * @apiNote 判断直播状态
     * @author yepk
     * @date 2019/3/12 9:10
     */
    public static int getLiveStatus(Date startTime, Date endTime) {
        Date nowTime = new Date();
        if (nowTime.before(startTime)) {
            return 0;
        }
        if (nowTime.after(endTime)) {
            return 2;
        }
        return 1;

    }


    /**
     * 获取指定日期所在周的日期字符串列表
     *
     * @param date    指定的日期
     * @param pattern 日期格式
     * @return 结果
     */
    public static List<String> getWeekDays(Date date, String pattern) {
        //获取本周一
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        //周日期列表
        List<String> weekDays = new ArrayList<>();
        String monday = DateToString(cal.getTime(), pattern);
        weekDays.add(monday);
        //循环获取一周日期
        for (int i = 1; i < WEEK_DAY_AMOUNT; i++) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            String day = DateToString(cal.getTime(), pattern);
            weekDays.add(day);
        }
        return weekDays;
    }

    /**
     * 获取指定日期所在指定周期内的日期字符串列表
     *
     * @param date      指定的日期
     * @param pattern   日期格式
     * @param preDays   指定日期往前推的天数（非负整数）
     * @param totalDays 总共查询的天数（正整数，大于等于preDays。）
     * @return 结果
     */
    public static List<String> getPeriodDays(Date date, String pattern, int preDays, int totalDays) {
        if (preDays > totalDays) {
            return new ArrayList<>();
        }
        //获取指定的时间
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //周日期列表
        List<String> weekDays = new ArrayList<>();
        int maxDay = totalDays - preDays;
        int minDay = 0 - preDays;
        //日期设置到最 早的的一天
        cal.add(Calendar.DAY_OF_MONTH, minDay);
        //循环获日期列表
        for (int i = minDay; i < maxDay; i++) {
            String day = DateToString(cal.getTime(), pattern);
            weekDays.add(day);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return weekDays;
    }


    /**
     * 获取当前时间或之前或之后某一天的日期字符串
     *
     * @param pattern 字符串格式
     * @param dayDiff 与当天的天数差（正数-未来，负数-过去，0-当天）
     * @return 日期字符串
     */
    public static String getPreOrNextDayStr(String pattern, int dayDiff) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, dayDiff);
        Date time = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String dayStr = format.format(time);
        return dayStr;
    }

    /**
     * 获取当前时间或之前或之后某一天的日期字符串
     *
     * @param dayDiff 与当天的天数差（正数-未来，负数-过去，0-当天）
     * @return 日期字符串
     */
    public static Date getPreOrNextDay(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * @param date 时间
     * @return long 天数
     * @apiNote 获取参数时间 到现在的 天数
     * @author yepk
     * @date 2019/3/26 17:06
     */
    public static long getDateBetweenNowByDay(Date date) {
        long now = System.currentTimeMillis();
        long result = date.getTime() - now;
        long day = result / (1000 * 60 * 60 * 24);
        return result % (1000 * 60 * 60 * 24) / (1000 * 60 * 60) > 0 ? day + 1 : day;
    }

    public static Date getAddDay(Date time, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 是否超过限定时间
     *
     * @param time    传入时间戳
     * @param seconds 于现在相差几秒
     * @return
     */
    public static Boolean checkDifferenceBetweenSeconds(Long time, Long seconds) {

        long now = System.currentTimeMillis();
        long result = now - time;
        long l = result / 1000;
        if (l > seconds) {
            return false;
        } else {
            return true;
        }

    }
}
