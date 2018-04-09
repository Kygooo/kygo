package com.kygo.common.utils;

import java.util.regex.Pattern;

/**
 * zhangjun
 */
public class NumberUtils {
	/**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^1[3-9][0-9]{9}$";
    /**
	 * 手机号验证
	 * @param mobile
	 * @return
	 */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 获取指定位数的随机字符串
     * @author zhangjun
     *
     */
    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }
}
