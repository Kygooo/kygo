package com.kygo.job.context;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ServletContextHolder {
	
	public static String CUSTOMER_ID_KEY = "customer_id";
	
	public static ServletRequestAttributes getRequestAttributes() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes == null) {
			return null;
		}
		
		return (ServletRequestAttributes) requestAttributes;
	}
	
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = getRequestAttributes();
		if (attributes == null) {
			return null;
		} else {
			return attributes.getRequest();
		}
	}
	
	public static HttpSession getSession() {
		
		HttpServletRequest request = getRequest();
		if (request == null) {
			return null;
		} else {
			return request.getSession();
		}
	}
}
