package com.kygo.service.utils;

import org.springframework.beans.BeanUtils;

public class BeanCopyUtil {
	
	public static void copyPropertyValue(Object source, Object target){
		BeanUtils.copyProperties(source, target);
	}

}
