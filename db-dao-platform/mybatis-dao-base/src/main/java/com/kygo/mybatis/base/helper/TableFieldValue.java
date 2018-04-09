package com.kygo.mybatis.base.helper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据库字段值常量类
 * @author Keith Wang (王 杰)
 * @email wangjie01vcredit.com
 * @date 2017年11月6日
 * @version 1.0
 */
public class TableFieldValue {
	
	/**
	 * 文本字符串类型空值（由于集团DBA不允许数据库表设计字段值不能为空）
	 */
	public static final String TEXT_NULL = "";
	
	/**
	 * 时间空值（由于集团DBA不允许数据库表设计字段值不能为空）
	 */
	public static final Date DATE_NULL = new Date(0);
	
	/**
	 * 时间戳空值（由于集团DBA不允许数据库表设计字段值不能为空）
	 */
	public static final Timestamp TIME_NULL = new Timestamp(0);
	
	/**
	 * 长整型空值（由于集团DBA不允许数据库表设计字段值不能为空）
	 */
	public static final Long LONG_NULL = -1L;
	
	/**
	 * 整型空值（由于集团DBA不允许数据库表设计字段值不能为空）
	 */
	public static final Integer INTEGER_NULL = -1;
	
	public static final BigDecimal ZREO = new BigDecimal(0);
	
	/**
	 * 布尔类型:是
	 */
	public static final Boolean TRUE = true;
	
	/**
	 * 布尔类型:否
	 */
	public static final Boolean FALSE = false;
	
	/**
	 * 系统用户名(创建人，更新人默认是"System")
	 */
	public static final String SYSTEM_USER = "System";
	
	public static final String Y = "Y";
	public static final String N = "N";
			
	/**
	 * 字段值是否为空
	 * @param fieldValue
	 * @return
	 */
	public static boolean isEmpty(String fieldValue){
		if(fieldValue == null || fieldValue.equals(TEXT_NULL)){
			return true;
		}
		return false;
	}
	
	/**
	 * 时间值是否为空
	 * @param fieldValue
	 * @return
	 */
	public static boolean isEmpty(Date fieldValue){
		if(fieldValue == null){
			return true;
		}
		if(fieldValue.getTime() <= 0L){
			return true;
		}
		return false;
	}
	
	/**
	 * 时间戳值是否为空
	 * @param fieldValue
	 * @return
	 */
	public static boolean isEmpty(Timestamp fieldValue){
		if(fieldValue == null){
			return true;
		}
		if(TableFieldValue.TIME_NULL.equals(fieldValue)){
			return true;
		}
		return false;
	}
	
	/**
	 * 字段值是否为空
	 * @param fieldValue
	 * @return
	 */
	public static boolean isEmpty(Long fieldValue){
		if(fieldValue == null || fieldValue.compareTo(LONG_NULL) <= 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 字段值是否为空
	 * @param fieldValue
	 * @return
	 */
	public static boolean isEmpty(Integer fieldValue){
		if(fieldValue == null || fieldValue.compareTo(INTEGER_NULL) <= 0){
			return true;
		}
		return false;
	}
	
	public static String getEmptyIfNull(String value){
		if(value == null){
			return TEXT_NULL;
		}
		return value;
	}
	
	public static Date getEmptyIfNull(Date value){
		if(value == null){
			return DATE_NULL;
		}
		return value;
	}
	
	public static BigDecimal getEmptyIfNull(BigDecimal value){
		if(value == null){
			return ZREO;
		}
		return value;
	}
	
	public static boolean idIsNull(Long id){
		if(id == null){
			return true;
		}
		return id < 0L;
	}
	
	public static Timestamp now(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static void main(String[] strings) throws ParseException {
		System.out.println(isEmpty(1L));
		System.out.println(isEmpty(-1L));
		System.out.println(isEmpty(-2L));
		System.out.println(isEmpty(0L));
		System.out.println(isEmpty(new Date(0)));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simpleDateFormat.parse("1970-01-02");
		System.out.println("-------------------"+simpleDateFormat.format(DATE_NULL));
		System.out.println(isEmpty(date));
	}
}
