package com.kygo.redis.manger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;

public class ObjectRedisTemplate {

	private final Logger logger = LoggerFactory.getLogger(ObjectRedisTemplate.class);

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public ObjectRedisTemplate(){
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	/**
	 * 默认是 30天
	 */
	private final int defailtExpireTime = 3600 * 24 * 30;

	private RedisTemplate<String, Object> redisTemplate;

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 *            过期时间，单位是秒
	 */
	public void put(final String key, final Object value, final long seconds) {
		redisTemplate.execute((RedisConnection connection) -> {
			try {
				connection.set(key.getBytes(), objectMapper.writeValueAsBytes(value));
			} catch (JsonProcessingException e) {
				logger.error("ObjectRedisTemplate.put(): " + e.getMessage());
			}
			connection.expire(redisTemplate.getStringSerializer().serialize(key), seconds > 0 ? seconds : defailtExpireTime);
			return 1L;
		});
	}

	public void put(final String key, final Object value) {
		this.put(key, value, defailtExpireTime);
	}

	public <T> T get(final String key, final Class<T> clazz) {
		return redisTemplate.execute((RedisConnection connection) -> {
			if (connection.exists(key.getBytes())) {
				byte[] value = connection.get(key.getBytes());				
				try {
					return objectMapper.readValue(value, clazz);
				} catch (IOException e) {
					logger.debug("key = {}, value = {}", key, new String(value));
					logger.error("ObjectRedisTemplate.get(): " + e.getMessage());
				}
			}
			return null;
		});
	}

	public String get(final String key) {
		return get(key, String.class);
	}

	public void delete(final String... keys) {
		redisTemplate.execute((RedisConnection connection) -> {
			long result = 0;
			int length = keys.length;
			for (int i = 0; i < length; i++) {
				result = connection.del(keys[i].getBytes());
			}
			return result;
		});
	}
}
