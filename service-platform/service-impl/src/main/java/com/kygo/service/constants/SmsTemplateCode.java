package com.kygo.service.constants;

public class SmsTemplateCode {
	
	/**
	 * 通用短信 code
	 */
	public static final String SMS_REGISTER_CODE = "5";
	
	/**
	 * 登录短信模板code
	 */
	public static final String SMS_LOGIN_CODE = "6";

	/**
	 * 微信解绑短信模板code
	 */
	public static final String SMS_UNBIND_CODE = "7";

	/**
	 * 报价失败（该订单所有报价都失败）
	 */
	public static final String OFFER_FAIL_ALL = "8";
	
	/**
	 * 报价成功（该订单 出现报价成功，仅第一个成功）
	 */
	public static final String OFFER_SUCCESS = "9";
	
	/**
	 * 报价修改（该订单 报价结果完成，无成功且有修改）
	 */
	public static final String OFFER_MODIFY = "10";
	
	/**
	 * 核保成功（某家保险公司核保成功）
	 */
	public static final String UNDERWRITING_SUCCESS = "11";
	
	
	/**
	 * 核保拒绝（某家保险公司拒绝承保）
	 */
	public static final String UNDERWRITING_REJECT = "12";
	
	/**
	 * 核保修改（某家保险公司返回 核保需修改）
	 */
	public static final String UNDERWRITING_MODIFY = "13";
	
	/**
	 * 承包成功（接口返回23承保成功）
	 */
	public static final String INSURE_UNDERWRITING_SUCCESS = "14";

}
