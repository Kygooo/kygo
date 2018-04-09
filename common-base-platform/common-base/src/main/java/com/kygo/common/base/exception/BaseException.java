package com.kygo.common.base.exception;

public abstract class BaseException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private String debugMsg;
	
	private String msg;

	public BaseException(String msg, String debug) {
		this.msg = msg;
		this.debugMsg = debug;
	}

	public String getDebugMsg() {
		return debugMsg;
	}

	public void setDebugMsg(String debugMsg) {
		this.debugMsg = debugMsg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


}
