package com.kygo.common.base;

public enum StatusEnum {
	
	SUCCESS("success"),//请求成功
	FAIL("fail"),//请求错误 一般是服务器错误才返回,用户重新发起请求
	ERROR("error"),//请求失败 业务错误，用户修改数据后请求
	PLEASE_LOGIN("need_login");//需要重新登录
	
	private StatusEnum(String status){
		this.status = status;
	}
	
	private String status;

	public String getStatus() {
		return status;
	}

}
