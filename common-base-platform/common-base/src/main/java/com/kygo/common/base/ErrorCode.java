package com.kygo.common.base;

public enum ErrorCode implements ErrorCodeProtocol {
	//-- 基础类 -- BASE_开头 [100,200)预留
    SERVER_ERROR(100, "网络繁忙，请稍后再试"),
    HTTP_METHOD_NOT_ALLOWED(101, "请求方法不允许"),
    API_NOT_FOUND(102, "接口不存在"),
    ACCESS_DENIED(103, "请重新登录"),
    INVALID_PARAMTER(105, "参数错误"),
    NO_POWER(107, "没有权限访问"),
    OPERATION_LOCK(108, "亲爱的用户,您的操作过快！请稍候再试!"),
    VERIFY_CODE_ERROR(109, "校验码错误"),

    ;
    private Integer code;

    private String msg;

    private ErrorCode(Integer code, String msg) {
    	this.code = code;
        this.msg = msg;
    }
    
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    
}

