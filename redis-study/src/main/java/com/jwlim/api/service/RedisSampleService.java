package com.jwlim.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RedisSampleService {
	private final Logger log = LoggerFactory.getLogger(RedisSampleService.class.getName());

	
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;
	
	@Resource(name = "redisTemplate")//String
	private ValueOperations<String, Object> valueOperations;
	@Resource(name = "redisTemplate")//List
	private ListOperations<String, String> listOperations;
	@Resource(name = "redisTemplate")//Hash
	private HashOperations<String, String, String> hashOperations;
	@Resource(name = "redisTemplate")//Sets
	private SetOperations<String, String> setOperations;
	@Resource(name = "redisTemplate")//Sorted Set
	private ZSetOperations<String, String> zSetOperations;

	ObjectMapper mapper = new ObjectMapper();
	
	public String getRedisStringKeyInfo(String key) {
		return (String) redisTemplate.opsForValue().get(key);
	}
	
	public String setRedisStringKeyInfo(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
		return key+" succesfully inserted";
	}
	
	public String deleteStringKeyInfo(String key) {
		redisTemplate.opsForValue().getAndDelete(key);
		log.info("Redis key : " + key);
		log.info("Redis value : " + valueOperations.get(key));
		return key+" succesfully deleted";
	}
	
	public String getAllRedisStringKeyInfo() throws JsonProcessingException {
		Set<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().keys("*".getBytes());
		
		ArrayList<String> cacheKeyList=new ArrayList<>();
		for(byte[] key : keys) {
			cacheKeyList.add(new String(key));
		}
		return mapper.writeValueAsString(cacheKeyList);
	}
	
	public String getRedisHashKeyInfo(String key, String hashKey) throws JsonProcessingException {
		return mapper.writeValueAsString( redisTemplate.opsForHash().get(key, hashKey) );
	}
	
	public String setRedisHashKeyInfo(String key, String hashKey,String value) throws JsonProcessingException {
		hashOperations.put(key, hashKey, value);
		return mapper.writeValueAsString( hashOperations.get(key, hashKey) );
	}
	
}
