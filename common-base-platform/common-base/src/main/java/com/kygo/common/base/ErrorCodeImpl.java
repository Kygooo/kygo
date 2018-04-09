package com.kygo.common.base;

public class ErrorCodeImpl implements ErrorCodeProtocol {
	
	private Integer code;
	private String msg;
	
	public ErrorCodeImpl(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getCode() {
		return this.code;
	}

	@Override
	public String getMsg() {
		return this.msg;
	}

}
