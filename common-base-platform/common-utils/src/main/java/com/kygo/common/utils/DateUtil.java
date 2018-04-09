package com.kygo.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author :周忠友
 * @date ：2014-6-22 上午12:00:49
 * @since : 1.0
 */
public class DateUtil {
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final DateFormat FULL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static final DateFormat FULL_FT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	/**
	 * yyyyMMddHHmmssSSS
	 */
	public static final DateFormat FULL_NUM_FT = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	/**
	 * yyyy-MM-dd
	 */
	public static final DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * yyyyMMdd
	 */
	public static final DateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	/**
	/**
	 * yyyy-MM
	 */
	public static final DateFormat YYYY_MM = new SimpleDateFormat("yyyy-MM");
	
	/**
	 * 格式化日期 默认使用DateUtil.YYYY_MM_DD格式化
	 * 
	 * @param date
	 * @param fomat
	 * @return
	 */
	public static final String format(Date date, DateFormat fomat) {
		if (fomat == null)
			return format(date);
		return fomat.format(date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static final String format(Date date) {
		return DateUtil.YYYY_MM_DD.format(date);
	}
	
	public static final Date parse(String dateStr, DateFormat fomat) {
		try {
			
			if (fomat == null)
				return parse(dateStr);
			return fomat.parse(dateStr);
		} catch (ParseException e) {
			// e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 日期字符串转换为日期类型
	 * 
	 * @param dateStr
	 * @return
	 */
	public static final Date parse(String dateStr) {
		try {
			return DateUtil.YYYY_MM_DD.parse(dateStr);
		} catch (ParseException e) {
			// e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取多少小时后的时间
	 * 
	 * @param hours
	 * @return
	 */
	public static Date getDateLaterHours(int hours) {
		return getDateLaterHours(new Date(), hours);
	}
	
	/**
	 * 获取多少小时后的时间
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date getDateLaterHours(Date date, int hours) {
		return getDateLater(date, Calendar.HOUR_OF_DAY, hours);
	}
	
	/**
	 * 获取多少天后的时间
	 *
	 * @param day
	 * @return
	 */
	public static Date getDateLaterDays(int days) {
		return getDateLaterDays(new Date(), days);
	}
	
	/**
	 * 获取多少天后的时间
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDateLaterDays(Date date, int days) {
		return getDateLater(date, Calendar.DAY_OF_YEAR, days);
	}
	
	/**
	 * 获取多少月后的时间
	 *
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getDateLaterMonths(int months) {
		return getDateLaterMonths(new Date(), months);
	}
	
	/**
	 * 获取多少月后的时间
	 *
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getDateLaterMonths(Date date, int months) {
		return getDateLater(date, Calendar.MONTH, months);
	}
	
	/**
	 * 获取多少年后的时间
	 *
	 * @param year
	 * @return
	 */
	public static Date getDateLaterYears(int years) {
		return getDateLaterYears(new Date(), years);
	}
	
	/**
	 * 获取多少年后的时间
	 *
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date getDateLaterYears(Date date, int years) {
		return getDateLater(date, Calendar.YEAR, years);
	}
	
	/**
	 * 获取多少（年、月、日）后的时间
	 *
	 * @param date
	 * @param field
	 * @param value
	 * @return
	 */
	public static Date getDateLater(int field, int value) {
		return getDateLater(new Date(), field, value);
	}
	
	/**
	 * 获取多少（年、月、日）后的时间
	 *
	 * @param date
	 * @param field
	 *            Calendar的field
	 * @param value
	 * @return
	 */
	public static Date getDateLater(Date date, int field, int value) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, value);
		return calendar.getTime();
	}
}
