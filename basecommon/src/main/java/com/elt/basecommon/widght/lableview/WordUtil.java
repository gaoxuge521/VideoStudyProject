package com.elt.basecommon.widght.lableview;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Administrator on 2018/3/5 11:05
 * 邮箱：android_gaoxuge@163.com
 */
public class WordUtil {

    public static List<String> getWords(@NonNull String source) {
        char[] dst = new char[source.length()];
        List<String> words = new ArrayList<>();
        //中文、空白字符当做分界符
        source.getChars(0, source.length(), dst, 0);
        int word_begin = 0;
        char preChar = ' ';
        for (int i = 0; i < dst.length; i++) {
            char item = dst[i];
            if (isChinese(item)) {
                if (!isChinese(preChar) && !Character.isSpaceChar(preChar)) {
                    words.add(source.substring(word_begin, i));
                }
                words.add(new String(new char[]{item}));
                word_begin = i + 1;
            } else if (Character.isSpaceChar(item)) {
                if ((!Character.isSpaceChar(preChar) && !isChinese(preChar))) {
                    words.add(source.substring(word_begin, i));
                }
                word_begin = i + 1;
            } else if (!Character.isSpaceChar(item) && i == dst.length - 1) {
                words.add(source.substring(word_begin, i + 1));
            }
            preChar = item;
        }
        return words;
    }

    public static boolean isChinese(String string) {
        if (string.replaceAll("[\u4e00-\u9fa5]*", "").length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isChinese(char item) {
        return (item >= 0x4e00) && (item <= 0x9fbb);
    }
}
