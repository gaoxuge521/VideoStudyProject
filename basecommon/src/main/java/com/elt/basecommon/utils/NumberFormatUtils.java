package com.elt.basecommon.utils;

import java.text.DecimalFormat;

/**
 * String 转 Number
 */

public class NumberFormatUtils {

    public static int getInteger(Object intValue, int defaultValue) {

        if(intValue == null || "".equals(intValue.toString().trim())){
            return defaultValue;
        }

        try {
            return Integer.valueOf(intValue.toString());
        } catch (Exception e) {

            try{
                return Double.valueOf(intValue.toString()).intValue();
            }catch (Exception eDouble){
                return defaultValue;
            }
        }
    }

    public static long getLong(Object longValue, long defaultValue) {

        if(longValue == null || "".equals(longValue.toString().trim())){
            return defaultValue;
        }

        try {
            return Long.valueOf(longValue.toString());
        } catch (Exception e) {

            try{
                return Double.valueOf(longValue.toString()).longValue();
            }catch (Exception eDouble){
                return defaultValue;
            }
        }
    }

    public static float getFloat(Object floatValue, float defaultValue) {

        if(floatValue == null || "".equals(floatValue.toString().trim())){
            return defaultValue;
        }

        try {
            return Float.valueOf(floatValue.toString());
        } catch (Exception e) {

            try{
                return Double.valueOf(floatValue.toString()).floatValue();
            }catch (Exception eDouble){
                return defaultValue;
            }
        }
    }

    public static double getDouble(Object doubleValue, double defaultValue) {

        if(doubleValue == null || "".equals(doubleValue.toString().trim())){
            return defaultValue;
        }

        try {
            return Double.valueOf(doubleValue.toString());
        } catch (Exception e) {
                return defaultValue;
        }
    }

    /**
     * double转String,保留小数点后两位
     * @param num
     * @return
     */
    public static String doubleToString(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        String format;
        try {
            format = new DecimalFormat("0.00").format(num);
        } catch (Exception e) {
            format = num+"";
        }
        return format;
    }
}
