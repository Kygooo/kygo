package com.kygo.common.utils;
/**
 * 验证工具
 * @author zhangjun
 *
 */
public class RegExpUtils {

    /**
     * 
     * @param value
     * @param min
     * @param max
     */
    public static boolean length(String value, int min, int max) {
        int length = length(value);
        return length <= max && length >= min;
    }
    
    /**
     * 字节长度
     * 
     * @param value
     * @return int
     */
    public static int length(String value) {
        String content = value.replaceAll("[^\\x00-\\xff]", "**");
        return content.length();
    }
    
    public static boolean isAllCharNumber(String value) {
        return value.matches("^[A-Za-z0-9]+$");
    }
    
    public static boolean isEmail(String value) {
        return value.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
    }
    
    public static void main(String[] args){
    	System.out.println(isAllCharNumber("2323*fffffff"));;
    	
    	System.out.println(isAllCharNumber("2323fffffff"));
    	
    	System.out.println(isAllCharNumber("2323fff?ffff"));
    	
    	System.out.println(isEmail("22@qq.com"));
    	
    	System.out.println(isEmail("22AAAAA@^qq.com"));
    	
    	System.out.println(isEmail("22AAAAAqq.com"));
    }

}
