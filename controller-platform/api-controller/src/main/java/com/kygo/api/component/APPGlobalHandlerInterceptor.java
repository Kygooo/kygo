package com.kygo.api.component;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class APPGlobalHandlerInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// 指定允许其他域名访问
		response.addHeader("Access-Control-Allow-Origin", "*");
		// 响应类型
		response.addHeader("Access-Control-Allow-Methods", "POST");
		// 响应头设置
		response.addHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		
		return super.preHandle(request, response, handler);
	}
}
