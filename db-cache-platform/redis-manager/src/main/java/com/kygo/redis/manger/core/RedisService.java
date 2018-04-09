package com.kygo.redis.manger.core;

import com.kygo.redis.manger.ObjectRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {
	
	private static final String SEPARATOR = ":";

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private ObjectRedisTemplate redisTemplate;

	/**
	 * 缓存键值为key的值
	 * 
	 * @param key
	 * @param value
	 */
	public void setObject(String key, Object value) {
		redisTemplate.put(key, value);
	}
	
	public Object getObject(String key){
		return redisTemplate.get(key);
	}

	/**
	 * 缓存键值为key的值
	 * 
	 * @param key
	 * @param value
	 * @param seconds(过期时间，单位为秒)
	 */
	public void setObject(String key, Object value, long seconds) {
		redisTemplate.put(key, value, seconds);
	}
	
	/**
	 * 缓存键值为key的值
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}
	
	public String getValue(String key){
		return stringRedisTemplate.opsForValue().get(key);
	}

	/**
	 * 缓存键值为key的值
	 * 
	 * @param key
	 * @param value
	 * @param timeout(过期时间，单位为秒)
	 */
	public void put(String key, String value, long timeout) {
		put(key, value, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 缓存键值为key的值
	 * 
	 * @param key
	 * @param value
	 * @param timeout(过期时间)
	 * @param unit
	 */
	public void put(String key, String value, long timeout, TimeUnit unit) {
		stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
	}
	
	public String get(String key){
		return stringRedisTemplate.opsForValue().get(key);
	}

	/**
	 * 缓存用户的一个对象的值
	 * @param userId
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public void save(Long userId, String key, Object value, long timeout){
		setObject(buildRedisKey(Long.toString(userId), key), value, timeout);
	}
	
	public void save(Long userId, String key, Object value){
		setObject(buildRedisKey(Long.toString(userId), key), value);
	}
	
	public Object getObject(Long userId, String key){
		return getObject(buildRedisKey(Long.toString(userId), key));
	}
	
	public <T> T getObject(String key, Class<T> clazz) {
		return redisTemplate.get(key, clazz);
	}
	
	public void remove(String key){
		stringRedisTemplate.delete(key);
	}
	
	public void remove(Collection<String> keyList){
		stringRedisTemplate.delete(keyList);
	}
	
	public void remove(String userId, String key){
		redisTemplate.delete(buildRedisKey(userId, key));
	}
	
	public void remove(String userId, String... keys){
		redisTemplate.delete(buildRedisKey(userId, keys));
	}
	
	public String buildRedisKey(String key, String...keys){
		StringBuilder stringBuilder = new StringBuilder(key);
		for(String k : keys){
			stringBuilder.append(SEPARATOR).append(k == null ? "":k);
		}
		return stringBuilder.toString();
	}
	
	public String getSeparator(){
		return SEPARATOR;
	}
}
