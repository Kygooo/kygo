package com.kygo.api.controller;

import com.cdvcredit.common.utils.StringUtils;
import com.kygo.api.context.ServletContextHolder;
import com.kygo.api.exception.NeedLoginException;
import com.kygo.common.base.exception.BusinessException;
import com.kygo.redis.manger.core.RedisService;
import com.kygo.service.constants.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author zhangjun
 * @create 2017-11-28
 **/
public abstract class BaseController {

    @Autowired
    private RedisService redisService;

    protected Long checkToken(String token) throws NeedLoginException {
        if (StringUtils.isEmpty(token)) {
            throw new NeedLoginException();
        }
        String userIdKey = redisService.buildRedisKey(RedisKey.TOKEN_CUSTOMER_ID_PREFIX, token);
        String userId = redisService.get(userIdKey);
        try {
            Long customerId = Long.valueOf(userId);
            ServletContextHolder.getRequestAttributes().setAttribute(ServletContextHolder.CUSTOMER_ID_KEY, customerId, ServletRequestAttributes.SCOPE_REQUEST);
            return customerId;
        } catch (Exception e) {
        	throw new NeedLoginException();
        }
    }
    
    protected Long getCustomerId(String token) {
		if(StringUtils.isEmpty(token)){
			return null;
		}
		String userIdKey = redisService.buildRedisKey(RedisKey.TOKEN_CUSTOMER_ID_PREFIX, token);
        String userId = redisService.get(userIdKey);
        try {
            Long customerId = Long.valueOf(userId);
            ServletContextHolder.getRequestAttributes().setAttribute(ServletContextHolder.CUSTOMER_ID_KEY, customerId, ServletRequestAttributes.SCOPE_REQUEST);
            return customerId;
        } catch (Exception e) {
        	return null;
        }
	}

    protected void removeUserId(String token) throws BusinessException {
        String userIdKey = redisService.buildRedisKey(RedisKey.TOKEN_CUSTOMER_ID_PREFIX, token);
        redisService.remove(userIdKey);
    }

}
