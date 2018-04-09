package com.kygo.common.base.exception;

public class ParameterException extends BaseException {


	private static final long serialVersionUID = 1L;
	
	
	public ParameterException(String msg, String debug) {
		super(msg, debug);
	}
	
	public ParameterException(String debug) {
		super("参数错误", debug);
	}
	
}
