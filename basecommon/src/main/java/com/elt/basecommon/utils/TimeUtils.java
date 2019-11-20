package com.elt.basecommon.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 作者：Administrator on 2018/4/16 16:49
 * 邮箱：android_gaoxuge@163.com
 */
public class TimeUtils {

    public static String dateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.CHINA);
        return  simpleDateFormat.format(date);
    }
    public static String dateToStringMMtodd(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd", Locale.CHINA);
        return  simpleDateFormat.format(date);
    }
    public static String dateToStringMMtodd2(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd", Locale.CHINA);
        String format = simpleDateFormat.format(date);
        String[] split = format.split("/");
        if(split.length>1){
            String yue = NumberFormatUtils.getInteger(split[0],0)+"";
            String ri = NumberFormatUtils.getInteger(split[1],0)+"";
            return yue+"/"+ri;

        }
        return  simpleDateFormat.format(date);
    }

    public static Date stringToDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.CHINA);
        try {
            return  simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Date stringMMddToDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd", Locale.CHINA);
        try {
            return  simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
    public static String dateToStringyyyyMMdd(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return  simpleDateFormat.format(date);
    }

    public static String dateToStringyyyyMM(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
        return  simpleDateFormat.format(date);
    }
    public static Date stringToDateByymd(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            return  simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 从时间(毫秒)中提取出时间(时:分)
     * 时间格式:  时:分
     *
     * @param millisecond
     * @return
     */
    public static String getTimeFromMillisecond(Long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        Date date = new Date(millisecond);
        String timeStr = simpleDateFormat.format(date);
        return timeStr;
    }

    /**
     * 从时间(秒)中提取出时间(时:分)
     * 时间格式:  时:分
     *
     * @param millisecond
     * @return
     */
    public static String getTimeFromMilli(Long millisecond,String type) {
        SimpleDateFormat simpleDateFormat;
        if(TextUtils.isEmpty(type)){
             simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        }else{
             simpleDateFormat = new SimpleDateFormat(type,Locale.CHINA);
        }

        Date date = new Date(millisecond*1000);
        String timeStr = simpleDateFormat.format(date);
        return timeStr;
    }
    /**
     * 从时间(秒)中提取出时间(时:分)
     * 时间格式:  时:分
     *
     * @param millisecond
     * @return
     */
    public static String getTimeFromMilli(Long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        Date date = new Date(millisecond*1000);
        String timeStr = simpleDateFormat.format(date);
        return timeStr;
    }

    /**
     * 从时间(秒)中提取出时间(时:分)
     * 时间格式:  时:分
     *
     * @param date
     * @return
     */
    public static String getTimeFromMilli(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        String timeStr = simpleDateFormat.format(date);
        return timeStr;
    }
    /**
     * 计算两个data之间的时间差天数
     * @param date
     * @param date2
     * @return
     */
    public static int getDifferenceDays(Date date,Date date2){
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        startCalendar.setTime(date);
        Calendar endCalendar = Calendar.getInstance(Locale.CHINA);
        endCalendar.setTime(date2);
        long between_days = (endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis()) / (1000 * 3600 * 24);
        return Math.abs((int)between_days)+1;
    }

    /**
     * 计算两个data之间的时间差天数
     * @param date
     * @param date2
     * @return
     */
    public static int getDifferenceDays2(Date date,Date date2){
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        startCalendar.setTime(date);
        Calendar endCalendar = Calendar.getInstance(Locale.CHINA);
        endCalendar.setTime(date2);
        long between_days = (endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis()) / (1000 * 3600 * 24);
        return Math.abs((int)between_days);
    }

    public static String getTimeByHour(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("hh:mm",Locale.CHINA);
        return format.format(date);
    }

    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("MM-dd",Locale.CHINA);
        return format.format(date);
    }
    public static String getTime2(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.CHINA);
        return format.format(date);
    }

    /*获取星期几*/
    public static String getWeek(Date data){
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
            default:
                return "";
        }
    }

    // 根据年月日计算年龄,birthTimeString:"1994-11-14"
    public static int getAgeFromBirthTime(String birthTimeString) {
        // 先截取到字符串中的年、月、日
        String strs[] = birthTimeString.trim().split("-");
        int selectYear = Integer.parseInt(strs[0]);
        int selectMonth = Integer.parseInt(strs[1]);
        int selectDay = Integer.parseInt(strs[2]);
        // 得到当前时间的年、月、日
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayNow = cal.get(Calendar.DATE);

        // 用当前年月日减去生日年月日
        int yearMinus = yearNow - selectYear;
        int monthMinus = monthNow - selectMonth;
        int dayMinus = dayNow - selectDay;

        int age = yearMinus;// 先大致赋值
        if (yearMinus < 0) {// 选了未来的年份
            age = 0;
        } else if (yearMinus == 0) {// 同年的，要么为1，要么为0
            if (monthMinus < 0) {// 选了未来的月份
                age = 0;
            } else if (monthMinus == 0) {// 同月份的
                if (dayMinus < 0) {// 选了未来的日期
                    age = 0;
                } else if (dayMinus >= 0) {
                    age = 1;
                }
            } else if (monthMinus > 0) {
                age = 1;
            }
        } else if (yearMinus > 0) {
            if (monthMinus < 0) {// 当前月>生日月
            } else if (monthMinus == 0) {// 同月份的，再根据日期计算年龄
                if (dayMinus < 0) {
                } else if (dayMinus >= 0) {
                    age = age + 1;
                }
            } else if (monthMinus > 0) {
                age = age + 1;
            }
        }
        return age;
    }

    // 根据时间戳计算年龄
    public static int getAgeFromBirthTime(long birthTimeLong) {
        Date date = new Date(birthTimeLong * 1000l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String birthTimeString = format.format(date);
        return getAgeFromBirthTime(birthTimeString);
    }


}
