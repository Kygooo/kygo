package com.kygo.redis.manger;

/**
 * zhangjun
 */
public interface CacheKeyPrefix {
	
	static String CUSTOMER_LOGIN_TOKEN = "Customer_login_token_";
	static String EMPLOYEE_LOGIN_TOKEN = "Employee_login_token_";
	static String RMCLOUD_ACCESS_TOKEN = "Rmcloud_access_token_";//风控云accessToken
}
