package com.kygo.common.base.exception;

/**
 * @author 周忠友
 * @version 1.0
 * @date 2017/12/14
 * 后台业务异常
 */
public class BehindException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code = 500;

	public BehindException(String msg){
        super(msg);
    }

    public BehindException(String msg,int code){
		super(msg);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
