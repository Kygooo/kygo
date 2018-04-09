package com.kygo.api.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarUtil {
	
	private static String express = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
	
	public static final boolean isCarLicenseNo(String carLicenseNo){
	    if (carLicenseNo.length() == 7){
	      Matcher matcher = Pattern.compile(express).matcher(carLicenseNo);
	      return matcher.matches();
	    }
	    return false;
	}
	
	public static void main(String[] args){
		DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
		Date regDate = null;
		try {
			regDate = dateFormat.parse("2017-03-3");
		} catch (Exception e) {
			
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -9);
		if(regDate.after(calendar.getTime())){
			System.out.println("小于9个月");
		}else{
			System.out.println("大于9个月");
		}
		
		System.out.println(isCarLicenseNo("京A32454"));
	}

}
