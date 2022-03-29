package com.jwlim.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwlim.api.service.RedisSampleService;



@RestController
public class RedisSampleController {
	@Autowired
	private RedisSampleService redisSampleService;

	@PostMapping(value = "/getRedisStringKeyInfo")
	public String getRedisStringValue(
			@RequestParam(required = true) String key
			) throws Exception {
		return redisSampleService.getRedisStringKeyInfo(key);
	}
	
	@PostMapping(value = "/setRedisStringKeyInfo")
	public String setRedisStringKeyInfo(
			@RequestParam(required = true) String key,
			@RequestParam(required = true) String value
			) throws Exception {
		return redisSampleService.setRedisStringKeyInfo(key,value);
	}
	
	@PostMapping(value = "/deleteRedisKeyInfo")
	public String deleteRedisKeyInfo(
			@RequestParam(required = true) String key
			) throws Exception {
		return redisSampleService.deleteStringKeyInfo(key);
	}
	
	@PostMapping(value = "/getAllRedisKeyInfo")
	public String getAllRedisKeyInfo(
			) throws Exception {
		return redisSampleService.getAllRedisStringKeyInfo();
	}
	
	@PostMapping(value = "/getRedisHashKeyInfo")
	public String getRedisHashKeyInfo(
			@RequestParam(required = true) String key,
			@RequestParam(required = true) String hashKey
			) throws Exception {
		return redisSampleService.getRedisHashKeyInfo(key,hashKey);
	}
	
	@PostMapping(value = "/setRedisHashKeyInfo")
	public String setRedisHashKeyInfo(
			@RequestParam(required = true) String key,
			@RequestParam(required = true) String hashKey,
			@RequestParam(required = true) String value
			) throws Exception {
		return redisSampleService.setRedisHashKeyInfo(key,hashKey,value);
	}
}
