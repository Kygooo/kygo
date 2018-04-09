package com.kygo.common.base.exception;


/**
 * 异常
 * 
 * @author Jxf
 *
 */
public class BusinessException extends BaseException {
	
	private static final long serialVersionUID = 1L;
	
	
	public BusinessException(String msg, String deubgMesg){
		super(msg, deubgMesg);
	}
	
	public BusinessException(String msg){
		super(msg, null);
	}
}
