/*
 * Copyright (c) 2017.
 */

package com.elt.basecommon.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gaoxuge
 * 各种验证的正则表达式
 */
public class VerifyUtils {
    private static final DecimalFormat amountFormat = new DecimalFormat("###,###,###,##0.00");
    /**
     * 根据日期判断星座
     *
     * @param month
     * @param day
     * @return
     */
    public static String getAstro(int month, int day) {
        String[] starArr = {"魔羯座", "水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座"};
        int[] DayArr = {22, 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22};  // 两个星座分割日

        if (month <= 0 || day <= 0) {
            return "猴年马月座";
        } else if (month > 12 || day > 31) {
            return "猴年马月座";
        }

        int index = month;
        // 所查询日期在分割日之前，索引-1，否则不变
        if (day < DayArr[month - 1]) {
            index = index - 1;
        }
        // 返回索引指向的星座string
        return starArr[index];
    }

    /**
     * 隐藏手机中间4位号码
     * 130****0000
     *
     * @param mobile_phone 手机号码
     * @return 130****0000
     */
    public static String hideMobilePhone4(String mobile_phone) {
        if (mobile_phone.length() != 11) {
            return "手机号码不正确";
        }
        return mobile_phone.substring(0, 3) + "****" + mobile_phone.substring(7, 11);
    }

    /**
     * 格式化银行卡 加*
     * 3749 **** **** 330
     *
     * @param cardNo 银行卡
     * @return 3749 **** **** 330
     */
    public static String formatCard(String cardNo) {
        if (cardNo.length() < 8) {
            return "银行卡号有误";
        }
        String card = "";
        card = cardNo.substring(0, 4) + " **** **** ";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

    /**
     * 银行卡后四位
     *
     * @param cardNo
     * @return
     */
    public static String formatCardEnd4(String cardNo) {
        if (cardNo.length() < 8) {
            return "银行卡号有误";
        }
        String card = "";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        联通：130、131、132、152、155、156、185、186
        电信：133、153、180、189、（1349卫通）
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
        */
        String telRegex = "[1][35847]\\d{9}";
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles) || mobiles.length() != 11) {
            return false;
        } else {
            return match(telRegex, mobiles); //mobiles.matches(telRegex);
        }
    }

    /**
     * 验证邮箱
     *
     * @param str 待验证的字符串
     * @return 如果是符合的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isEmail(String str) {
        String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return match(regex, str);
    }

    /**
     * 验证IP地址
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isIP(String str) {
        String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
        return match(regex, str);
    }

    /**
     * 验证网址Url
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsUrl(String str) {
        String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        return match(regex, str);
    }

    /**
     * 验证电话号码
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsTelephone(String str) {
        String regex = "^(\\d{3,4}-)?\\d{6,8}$";
        return match(regex, str);
    }

    /**
     * 验证输入密码条件(字符与数据同时出现)
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsPassword(String str) {
        if (TextUtils.isEmpty(str))
            return false;
        String regex = "^[A-Za-z0-9]+$";
        return match(regex, str);
    }

    /**
     * 验证输入密码长度 (6-12位)
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsPasswordLength(String str) {
        String regex = "^[A-Za-z0-9]{6,12}$";
        return match(regex, str);
    }

    /**
     * 验证昵称 ( 包含字母、数字和汉字）
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsNickName(String str) {
        String regex = "^[A-Za-z0-9\\u4e00-\\u9fa5]{2,12}$";
        return match(regex, str);
    }


    /**
     * 验证输入邮政编号
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsPostalcode(String str) {
        String regex = "^\\d{6}$";
        return match(regex, str);
    }

    /**
     * 验证输入手机号码
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsHandset(String str) {
        String regex = "^[1]+[3,5]+\\d{9}$";
        return match(regex, str);
    }

    /**
     * 验证输入身份证号
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsIDcard(String str) {
        String regex = "(^\\d{18}$)|(^\\d{15}$)";
        return match(regex, str);
    }

    /**
     * 验证输入两位小数
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsDecimal(String str) {
        String regex = "^[0-9]+(.[0-9]{2})?$";
        return match(regex, str);
    }

    /**
     * 验证输入一年的12个月
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsMonth(String str) {
        String regex = "^(0?[[1-9]|1[0-2])$";
        return match(regex, str);
    }

    /**
     * 验证输入一个月的31天
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsDay(String str) {
        String regex = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
        return match(regex, str);
    }

    /**
     * 验证日期时间
     *
     * @param str 待验证的字符串
     * @return 如果是符合网址格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isDate(String str) {
        // 严格验证时间格式的(匹配[2002-01-31], [1997-04-30],
        // [2004-01-01])不匹配([2002-01-32], [2003-02-29], [04-01-01])
        // String regex =
        // "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((01,3-9])|(1[0-2]))-(29|30)))))$";
        // 没加时间验证的YYYY-MM-DD
        // String regex =
        // "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
        // 加了时间验证的YYYY-MM-DD 00:00:00
        String regex = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
        return match(regex, str);
    }

    /**
     * 验证数字输入
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsNumber(String str) {
        String regex = "^[0-9]*$";
        return match(regex, str);
    }

    /**
     * 验证非零的正整数
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsIntNumber(String str) {
        String regex = "^\\+?[1-9][0-9]*$";
        return match(regex, str);
    }

    /**
     * 验证大写字母
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsUpChar(String str) {
        String regex = "^[A-Z]+$";
        return match(regex, str);
    }

    /**
     * 验证小写字母
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsLowChar(String str) {
        String regex = "^[a-z]+$";
        return match(regex, str);
    }

    /**
     * 验证验证输入字母
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsLetter(String str) {
        String regex = "^[A-Za-z]+$";
        return match(regex, str);
    }

    /**
     * Return whether input matches the regex.
     *
     * @param regex The regex.
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * Return whether input matches regex of Chinese character.
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isZh(final CharSequence input) {
        return isMatch(REGEX_ZH, input);
    }

    /**
     * 15和18位身份证校验,粗略的校验
     * @author lyl
     * @param idCard
     * @return
     */
    public static boolean is18ByteIdCard(String idCard){
        Pattern   pattern1 = Pattern.compile("^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$"); //粗略的校验
        Matcher matcher = pattern1.matcher(idCard);
        if(matcher.matches()){
            return true;
        }
        return false;
    }

    /**
     * Regex of Chinese character.
     */
    public static final String REGEX_ZH = "^[\\u4e00-\\u9fa5]+$";
    /**
     * 验证验证输入汉字
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsChinese(String str) {
        String regex = "^[\u4e00-\u9fa5],{0,}$";
        return match(regex, str);
    }

    /**
     * 验证验证输入字符串
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsLength(String str) {
        String regex = "^.{8,}$";
        return match(regex, str);
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

// 3. 检查字符串重复出现的词
//
// private void btnWord_Click(object sender, EventArgs e)
// {
// System.Text.RegularExpressions.MatchCollection matches =
// System.Text.RegularExpressions.Regex.Matches(label1.Text,
//
// @"\b(?<word>\w+)\s+(\k<word>)\b",
// System.Text.RegularExpressions.RegexOptions.Compiled |
// System.Text.RegularExpressions.RegexOptions.IgnoreCase);
// if (matches.Count != 0)
// {
// foreach (System.Text.RegularExpressions.Match match in matches)
// {
// string word = match.Groups["word"].Value;
// MessageBox.Show(word.ToString(),"英文单词");
// }
// }
// else { MessageBox.Show("没有重复的单词"); }
//
//
// }
//
// 4. 替换字符串
//
// private void button1_Click(object sender, EventArgs e)
// {
//
// string strResult =
// System.Text.RegularExpressions.Regex.Replace(textBox1.Text,
// @"[A-Za-z]\*?", textBox2.Text);
// MessageBox.Show("替换前字符:" + "\n" + textBox1.Text + "\n" + "替换的字符:" + "\n"
// + textBox2.Text + "\n" +
//
// "替换后的字符:" + "\n" + strResult,"替换");
//
// }
//
// 5. 拆分字符串
//
// private void button1_Click(object sender, EventArgs e)
// {
// //实例: 甲025-8343243乙0755-2228382丙029-32983298389289328932893289丁
// foreach (string s in
// System.Text.RegularExpressions.Regex.Split(textBox1.Text,@"\d{3,4}-\d*"))
// {
// textBox2.Text+=s; //依次输出 "甲乙丙丁"
// }
//
// }


    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (TextUtils.isEmpty(s) || !Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (TextUtils.isEmpty(s) || !Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = s.length();
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (TextUtils.isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (TextUtils.isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 金额格式化
     *
     * @param value 数值
     * @return
     */
    public static String getAmountValue(double value) {
        return amountFormat.format(value);
    }

    /**
     * 金额格式化
     *
     * @param value 数值
     * @return
     */
    public static String getAmountValue(String value) {
        if (TextUtils.isEmpty(value)) {
            return "0";
        }
        return amountFormat.format(Double.parseDouble(value));
    }


    /**
     * 四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundUp(BigDecimal value, int digit) {
        return value.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
    }
    /**
     * 获取百分比（乘100）
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getPercentValue(BigDecimal value, int digit) {
        BigDecimal result = value.multiply(new BigDecimal(100));
        return getRoundUp(result, digit);
    }

    /**
     * 获取百分比（乘100）
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getPercentValue(double value, int digit) {
        BigDecimal result = new BigDecimal(value);
        return getPercentValue(result, digit);
    }

    /**
     * 获取百分比（乘100,保留两位小数）
     *
     * @param value 数值
     * @return
     */
    public static String getPercentValue(double value) {
        BigDecimal result = new BigDecimal(value);
        return getPercentValue(result, 2);
    }

}
