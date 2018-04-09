package com.kygo.api.component;

import com.kygo.api.exception.NeedLoginException;
import com.kygo.api.response.Response;
import com.kygo.common.base.ErrorCode;
import com.kygo.common.base.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
	
	private static final String ACTIVE_PROFILE_PROD = "prod";
	
	@Value("${spring.profiles.active}")
	private String activeProfile;
		
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Response jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		if(e instanceof NeedLoginException){
			return Response.needLoginResponse("请先登录", "token无效或者token过期，请先登录再重试！");
		}
		if(e instanceof BaseException){
			BaseException exception = (BaseException)e;
			return Response.fail(exception.getMsg(), getDebugMessage(exception.getDebugMsg()));
		}
		if(e instanceof org.springframework.web.bind.MethodArgumentNotValidException){
			logger.error("Invalid argument message: ", e);
			return Response.error(ErrorCode.INVALID_PARAMTER.getMsg(), e.getMessage());
		}else if (e instanceof HttpRequestMethodNotSupportedException) {
			ErrorCode errorCode = ErrorCode.HTTP_METHOD_NOT_ALLOWED;
			logger.error("Exception message: " + errorCode.getCode() + " - " + errorCode.getMsg());
			return Response.fail(errorCode.getMsg());
		} else if (e instanceof HttpMessageNotReadableException) {
			logger.error("Exception message: ", e);
			return Response.fail(ErrorCode.INVALID_PARAMTER.getMsg(), e.getMessage());
		} else {
			logger.error("Exception message: ", e);
			return Response.error(ErrorCode.SERVER_ERROR.getMsg(), e.getMessage());
		}
	}
	
	private String getDebugMessage(String debugMessage){
		return ACTIVE_PROFILE_PROD.equals(activeProfile) ? null : debugMessage;
	}
}
