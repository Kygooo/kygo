package com.kygo.api.logback.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.kygo.api.context.ServletContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestConverter extends ClassicConverter {
	
	@Override
	public String convert(ILoggingEvent event) {
		
		ServletRequestAttributes attributes = ServletContextHolder.getRequestAttributes();
		if (attributes == null) {
			return "";
		}
		
		HttpServletRequest request = attributes.getRequest();
		if (request == null) {
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("[session: ")
		  .append(attributes.getSessionId().substring(0, 12))
		  .append(", api: ")
		  .append(request.getRequestURI())
		  .append(", customerId: ")
		  .append(attributes.getAttribute(ServletContextHolder.CUSTOMER_ID_KEY, ServletRequestAttributes.SCOPE_REQUEST))
		  .append("]");
		
		return sb.toString();
	}
}
