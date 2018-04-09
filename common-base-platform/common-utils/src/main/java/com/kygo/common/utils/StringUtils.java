package com.kygo.common.utils;


/**
 * @author zhangjun
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * desc:判断密码是否加密
     *
     * @param str
     * @return
     */
    public static boolean isMd5_32(String str) {
        String regex = "^[0-9A-Z-a-z]{32}$";
        return str.matches(regex);
    }

    /**
     * desc:判断字符串长度  <=300
     *
     * @param s
     * @return
     */
    public static int getWordCount(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 300)
                length++;
            else
                length += 2;

        }
        return length;
    }

}
