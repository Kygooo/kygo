package com.kygo.api.aspect;

import com.cdvcredit.common.utils.SubstringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kygo.api.context.ServletContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * refer to : http://blog.didispace.com/springbootaoplog/
 * 记录请求日志（打印请求的参数，返回值等等）
 * @author keith
 *
 */
@Aspect
@Component
public class HttpRequestLogRecordAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
		
	private ObjectMapper mapper = new ObjectMapper();
	
	@Pointcut("execution(public * com.cdvcredit.api.app.controller..*.*(..))")
	public void apiLogRecord() {
		
	}
	
	@Before("apiLogRecord()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		String argsJson = mapper.writeValueAsString(joinPoint.getArgs());
		String printParamString = SubstringUtils.substring(argsJson);
		logger.debug("User-Agent: " + ServletContextHolder.getRequest().getHeader("User-Agent"));
		logger.debug("Request param: " + printParamString);
	}
	
	@AfterReturning(returning = "ret", pointcut = "apiLogRecord()")
	public void doAfterReturning(Object ret) throws Throwable {
		String printParamString = mapper.writeValueAsString(ret);
		logger.debug("Response data: " + printParamString);
	}
}
