package com.kygo.service.constants;

public class BusinessErrorCode {
	
    public static final class Dictionary{
    	
    	public static final int RECORD_EXIST_CODE = 601;
    	public static final String RECORD_EXIST_MSG = "数据已经存在!";

	}

	public static final class Customer{

		public static final int USER_IS_EXIST_CODE = 300;
		public static final String USER_IS_EXIST_MSG = "该手机号已经注册过！";
		
		public static final int USER_IS_NOT_EXIST_CODE = 301;
		public static final String USER_IS_NOT_EXIST_MSG = "用户未注册";
		
		public static final int VERIFY_CUSTOMER_FAILD_CODE = 302;
		public static final String VERIFY_CUSTOMER_FAILD_MSG = "用户名或密码错误";

		public static final int VERIFICATION_FAILD_CODE = 303;
		public static final String VERIFICATION_FAILD_MSG = "短信验证码错误";

		public static final int VERIFICATION_SEND_FAILD_CODE = 304;
		public static final String VERIFICATION_SEND_FAILD_MSG = "短信验证码发送失败";

	}


}

