package com.kygo.api.response;

import com.kygo.common.base.StatusEnum;
import io.swagger.annotations.ApiModelProperty;

public class Response {
	
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	private StatusEnum status;

	/**
	 * 响应码中文描述
	 */
	@ApiModelProperty(value = "描述")
	private String msg;
	
	@ApiModelProperty(value = "调试描述")
	private String debugMsg;

	/**
	 * 响应内容（对象）
	 */
	@ApiModelProperty(value = "响应内容")
	private Object content;
	
	private Response(StatusEnum status, String msg, String debugMsg, Object content){
		this.status = status;
		this.msg = msg;
		this.content = content;
		this.debugMsg = debugMsg;
	}
	
	public static Response needLoginResponse(String msg, String debugMsg){
		return new Response(StatusEnum.PLEASE_LOGIN, msg, debugMsg, null); 
	}
		
	public static Response success(){
		return new Response(StatusEnum.SUCCESS, null, null, "");
	}
	
	public static Response success(Object content){
		return new Response(StatusEnum.SUCCESS, null, null, content);
	}
	
	public static Response fail(String msg, String debug){
		return new Response(StatusEnum.FAIL, msg, debug, null);
	}
	
	public static Response fail(String msg){
		return new Response(StatusEnum.FAIL, msg, null, null);
	}
	
	public static Response error(String msg, String debug){
		return new Response(StatusEnum.ERROR, msg, debug, null);
	}
	
	public static Response error(String msg){
		return new Response(StatusEnum.ERROR, msg, null, null);
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDebugMsg() {
		return debugMsg;
	}

	public void setDebugMsg(String debugMsg) {
		this.debugMsg = debugMsg;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
