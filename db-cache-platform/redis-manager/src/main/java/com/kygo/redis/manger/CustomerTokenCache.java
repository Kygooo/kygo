package com.kygo.redis.manger;

import com.kygo.redis.manger.core.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 用户Token缓存
 * @author zhangjun
 *
 */
@Component
public class CustomerTokenCache {
	
	public static final long TOKEN_TIMEOUT = 20*60;//20分钟
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 清理之前的token，并缓存新的token
	 * 
	 * @param source
	 * @return
	 */
	public String saveToken(String source, String customerId) {
		String key = cacheKey(source, customerId);
		String existToken = redisService.getValue(key);
		if (existToken != null) {
			String tokenKey = cacheKey(source, existToken);
			redisService.remove(tokenKey);
		}
		redisService.remove(key);
		
		String token = UUID.randomUUID().toString();
		String tokenKey = cacheKey(source, token);
		redisService.put(tokenKey, customerId, TOKEN_TIMEOUT);
		redisService.put(key, token, TOKEN_TIMEOUT);
		return token;
	}
	
	/**
	 * 根据token获取用户id，并更新token过期时间
	 * 
	 * @param source
	 * @param token
	 * @return
	 */
	public String getCustomerId(String source, String token) {
		String tokenKey = cacheKey(source, token);
		String customerId = redisService.getValue(tokenKey);
		if (customerId != null) {
			redisService.put(tokenKey, customerId, TOKEN_TIMEOUT);
			redisService.put(cacheKey(source, customerId), token, TOKEN_TIMEOUT);
		}
		return customerId;
	}
	
	private String cacheKey(String source, String customerId) {
		return CacheKeyPrefix.CUSTOMER_LOGIN_TOKEN + source+ "_" + customerId;
	}
}
