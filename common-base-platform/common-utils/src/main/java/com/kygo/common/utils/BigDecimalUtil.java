package com.kygo.common.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {
	
	private static final BigDecimal ZERO = new BigDecimal(0);
	
	public static boolean isBiggerThanZero(BigDecimal value){
		return value.compareTo(ZERO) > 0 ;
	}
	
	public static String format(BigDecimal value){
		if(value == null){
			return "";
		}
		return value.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	}
	
	public static void main(String[] args){
		System.out.println(isBiggerThanZero(new BigDecimal(1)));
		System.out.println(format(new BigDecimal("2332.434334")));
		System.out.println(format(new BigDecimal("2332.454334")));
		System.out.println(format(new BigDecimal("0.454334")));
	}

}
