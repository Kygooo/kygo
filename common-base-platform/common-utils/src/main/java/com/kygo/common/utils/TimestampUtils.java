package com.kygo.common.utils;

import java.sql.Timestamp;

public class TimestampUtils {
	
	public static Timestamp now(){
		return new Timestamp(System.currentTimeMillis());
	}

}
