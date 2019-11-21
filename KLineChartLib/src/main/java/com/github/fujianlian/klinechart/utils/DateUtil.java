package com.github.fujianlian.klinechart.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 * Created by tifezh on 2016/4/27.
 */
public class DateUtil {
    public static SimpleDateFormat longTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static SimpleDateFormat shortTimeFormat = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private static String timeString;
    private static DatePattern pattern;


    /**
     * 枚举日期格式
     *
     * @author Administrator
     */
    public enum DatePattern {
        /**
         * 格式："yyyy-MM-dd HH:mm:ss"
         */
        ALL_TIME {
            public String getValue() {
                return "yyyy-MM-dd HH:mm:ss";
            }
        },
        /**
         * 格式："yyyy-MM"
         */
        ONLY_MONTH {
            public String getValue() {
                return "yyyy-MM";
            }
        },
        /**
         * 格式："yyyy-MM-dd"
         */
        ONLY_DAY {
            public String getValue() {
                return "yyyy-MM-dd";
            }
        },
        /**
         * 格式："yyyy-MM-dd HH"
         */
        ONLY_HOUR {
            public String getValue() {
                return "yyyy-MM-dd HH";
            }
        },
        /**
         * 格式："yyyy-MM-dd HH:mm"
         */
        ONLY_MINUTE {
            public String getValue() {
                return "yyyy-MM-dd HH:mm";
            }
        },
        /**
         * 格式："MM-dd"
         */
        ONLY_MONTH_DAY {
            public String getValue() {
                return "MM-dd";
            }
        },
        /**
         * 格式："MM-dd HH:mm"
         */
        ONLY_MONTH_SEC {
            public String getValue() {
                return "MM-dd HH:mm";
            }
        },
        /**
         * 格式："MM-dd HH:mm"
         */
        ONLY_MONTH_ALL_SEC {
            public String getValue() {
                return "MM-dd HH:mm:ss";
            }
        },
        /**
         * 格式："HH:mm:ss"
         */
        ONLY_TIME {
            public String getValue() {
                return "HH:mm:ss";
            }
        },
        /**
         * 格式："HH:mm"
         */
        ONLY_HOUR_MINUTE {
            public String getValue() {
                return "HH:mm";
            }
        },
        /**
         * 格式："ss"
         */
        ONLY_SECOND {
            public String getValue() {
                return "ss";
            }
        };

        public abstract String getValue();
    }

    //字符串转时间戳
    public static String getTime(String timeString, DatePattern pattern) {
        String timeStamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue());
        Date d;
        try {
            d = sdf.parse(timeString);
            long l = d.getTime();
            timeStamp = String.valueOf(l);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }

    //时间戳转字符串
    public static String getStrTime(String timeStamp, DatePattern pattern) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue());
        long l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    private DateUtil() {
    }

    ;


    /**
     * 往后推移时间
     *
     * @param date
     * @param minute
     * @return
     * @throws ParseException
     */
    public static String dealTime(String date, int minute) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sd.parse(date);
        // 把当前得到的时间用date.getTime()的方法写成时间戳的形式，再加上8小时对应的毫秒数
        long rightTime = (d.getTime() + minute * 60 * 1000);
        // 把得到的新的时间戳再次格式化成时间的格式
        String newtime = sd.format(rightTime);
        return sd.parse(newtime).getTime() + "";

    }


    /**
     * 将时分秒转换成时分
     *
     * @param time hh:mm:ss
     * @return hh:mm
     */
    public static String getOnlyMinute(String time) {
        if (time.length() > 5)
            return time.substring(0, time.length() - 3);
        else return "";
    }

    /**
     * 获取当前时间
     *
     * @return 返回当前时间，格式2015-12-3	10:54:21
     */
    public static String getNowDate(DatePattern pattern) {
        String dateString = null;
        Calendar calendar = Calendar.getInstance();
        Date dateNow = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        dateString = simpleDateFormat.format(dateNow);
        return dateString;
    }

    /**
     * 获取当前时间戳
     *
     * @return 返回当前时间，格式2015-12-3
     */
    public static Date getNowDate(String year, String month, String day) {
        Date date = null;
        try {
            date = sdf.parse(year + month + day);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将一个日期字符串转换成Data对象
     *
     * @param dateString 日期字符串
     * @param pattern    转换格式
     * @return 返回转换后的日期对象
     */
    public static Date stringToDate(String dateString, DatePattern pattern) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将date转换成字符串
     *
     * @param date    日期
     * @param pattern 日期的目标格式
     * @return
     */
    public static String dateToString(Date date, DatePattern pattern) {
        String string = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        string = simpleDateFormat.format(date);
        return string;
    }

    /**
     * 获取时间差  返回单位分钟
     *
     * @param start
     * @return
     */
    public static int getSpaceTime(long start) {
        long end = System.currentTimeMillis();
        int minute = (int) ((start * 1000 - end) / 1000 / 60);
        return minute;
    }

    /**
     * 获取指定日期周几
     *
     * @param date 指定日期
     * @return 返回值为： "周日", "周一", "周二", "周三", "周四", "周五", "周六"
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week < 0)
            week = 0;
        return weekDays[week];
    }

    /**
     * 获取指定日期对应周几的序列
     *
     * @param date 指定日期
     * @return 周一：1	周二：2	周三：3	周四：4	周五：5	周六：6	周日：7
     */
    public static int getIndexWeekOfDate(Date date) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		int index = calendar.get(Calendar.DAY_OF_WEEK);
//		if(index == 1){
//			return 7;
//		}else{
//			return --index;
//		}
        Calendar now = Calendar.getInstance();
        //一周第一天是否为星期天
        boolean isFirstSunday = (now.getFirstDayOfWeek() == Calendar.SUNDAY);
        //获取周几
        int weekDay = now.get(Calendar.DAY_OF_WEEK);
        //若一周第一天为星期天，则-1
        if (isFirstSunday) {
            weekDay = weekDay - 1;
            if (weekDay == 0) {
                weekDay = 7;
            }
        }
        return weekDay;
        //若当天为2014年10月13日（星期一），则打印输出：1
        //若当天为2014年10月17日（星期五），则打印输出：5
        //若当天为2014年10月19日（星期日），则打印输出：7
    }


    //获取当前月的第一天
    public static String getMonthStartDate(DatePattern datePattern) {
//		SimpleDateFormat dateFormater = new SimpleDateFormat(
//				"yyyy-MM-dd");
        SimpleDateFormat dateFormater = new SimpleDateFormat(
                datePattern.getValue());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.getTime();
        return dateFormater.format(cal.getTime()) + "";

    }

    //获取当前月的最后一天
    public static String getMonthEndDate(DatePattern datePattern) {
        SimpleDateFormat dateFormater = new SimpleDateFormat(
                datePattern.getValue());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,
                cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dateFormater.format(cal.getTime());
    }

    /**
     * 返回当前月份
     *
     * @return
     */
    public static int getNowMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前月号
     *
     * @return
     */
    public static int getNowDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getNowYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前小时
     *
     * @return
     */
    public static int getNowHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR);
    }

    /**
     * 获取当前分钟
     *
     * @return
     */
    public static int getNowMinute() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 数据补0  如8月 返回08  9号返回09
     *
     * @param number
     * @return
     */
    public static String getTwoDigitNumber(int number) {
        if (number < 10) {
            return "0" + number;
        } else {
            return number + "";
        }
    }

    /**
     * 获取本月份的天数
     *
     * @param
     * @param
     * @return
     */
    public static int getNowDaysOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return daysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.DATE) + 1);
    }

    /**
     * 获取指定月份的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 对应天数
     */
    public static int daysOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if ((year % 4 == 0 && year % 100 == 0) || year % 400 == 0) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }


    /**
     * 推移时间
     *
     * @param stDate  被推移的当前时间
     * @param pattern 当前时间的时间格式 yyyy:MM:dd
     * @param amount  需要推移的天数 （1为 后一天 -1为前一天）
     * @return
     */
    public static String dateOfMove(String stDate, int amount, DatePattern pattern) {
        if (stDate == null)
            return null;
        Calendar c = Calendar.getInstance();
        Date date = stringToDate(stDate, pattern);
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, amount);// 今天+1天
        Date tomorrow = c.getTime();
        return dateToString(tomorrow, pattern);
    }

    /**
     * 推移时间
     *
     * @param stDate 被推移的当前时间
     * @param amount 需要推移的天数 （1为 后一天 -1为前一天）
     * @return
     */
    public static Date dateOfMove(Date stDate, int amount) {
        if (stDate == null)
            return null;
        Calendar c = Calendar.getInstance();
        c.setTime(stDate);
        c.add(Calendar.DAY_OF_MONTH, amount);// 今天+1天
        Date tomorrow = c.getTime();
        return tomorrow;
    }


    // a integer to xx:xx:xx 将秒转换为时分秒的字符串
    public static String secToTime(long time) {
        String timeStr = null;
        long hour = 0;
        long minute = 0;
        long second = 0;
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

    public static String unitFormat(long i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Long.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * 毫秒转换为 天数 小数数 分钟数 xx
     *
     * @param nmMillSecond
     * @return
     */
    public static MyTimeBean millSecondToTimeBean(long nmMillSecond) {
        MyTimeBean myTimeBean = new MyTimeBean();
        long millSecond = nmMillSecond % 1000 / 10;
        myTimeBean.setMillSecond(millSecond + "");
        nmMillSecond = nmMillSecond / 1000;
        long days = nmMillSecond / (60 * 60 * 24);

        //long hours = (nmMillSecond % ( 60 * 60 * 24)) / (60 * 60);
        long hour;
        if (days > 0) {
            hour = (nmMillSecond % (60 * 60 * 24)) / (60 * 60);//时
        } else {
            hour = nmMillSecond / 3600;//时
        }
        long minutes = (nmMillSecond % (60 * 60)) / 60;
        long seconds = nmMillSecond % 60;

        myTimeBean.setSecond(seconds + "");
        myTimeBean.setMinute(minutes + "");
        myTimeBean.setHour(hour + "");
        myTimeBean.setDay(days + "");
        return myTimeBean;
    }

    /**
     * 毫秒转天数+小时数+分钟数+秒数+毫秒数
     */
    public static class MyTimeBean {
        String day;
        String hour;
        String minute;
        String second;
        String millSecond;

        public MyTimeBean() {
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getMinute() {
            return minute;
        }

        public void setMinute(String minute) {
            this.minute = minute;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }

        public String getMillSecond() {
            return millSecond;
        }

        public void setMillSecond(String millSecond) {
            this.millSecond = millSecond;
        }
    }
}
