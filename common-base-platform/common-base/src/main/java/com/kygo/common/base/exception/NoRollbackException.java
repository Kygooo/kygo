package com.kygo.common.base.exception;

public class NoRollbackException extends BaseException {

	private static final long serialVersionUID = 1L;

	public NoRollbackException(String msg, String deubgMesg){
		super(msg, deubgMesg);
	}
	
	public NoRollbackException(String msg){
		super(msg, null);
	}
}
