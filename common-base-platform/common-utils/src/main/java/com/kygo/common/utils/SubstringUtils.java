package com.kygo.common.utils;

public final class SubstringUtils {
	
	private static final int DEFAULT_SUBSTRING_MAX_LENGTH = 2000;
	
	/**
     * 日志打印http请求的最大长度，如果超出最大长度讲截取字符串前面部分
     * @param json
     * @param maxLenght
     * @return
     */
    public static String substring(String json, int maxLenght)
    {
    	if(json == null){
			return "";
		}
		if(json.length() > maxLenght){
			return json.substring(0, maxLenght)+"...";
		}
		return json;
    }
    
    public static String substring(String json) {
		return substring(json, DEFAULT_SUBSTRING_MAX_LENGTH);
	}

}
