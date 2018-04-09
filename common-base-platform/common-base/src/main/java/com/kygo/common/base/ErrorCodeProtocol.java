package com.kygo.common.base;

public interface ErrorCodeProtocol {
	
	/**
	 * 错误代码
	 * 
	 * @return
	 */
	Integer getCode();
	
	/**
	 * 错误描述
	 * 
	 * @return
	 */
	String getMsg();
}
