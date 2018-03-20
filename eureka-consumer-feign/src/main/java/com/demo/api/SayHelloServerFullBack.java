package com.demo.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.demo.protocol.HelloRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@SuppressWarnings("rawtypes")
public class SayHelloServerFullBack implements ISayHelloServer {

	@Override
	public Map sayHello(String name, String say, String randomId) {
		log.info("进入服务消费者降级处理");
		Map<String, Object> errMap = new HashMap<String, Object>();
		errMap.put("name", "full_back_server_null_name");
		errMap.put("say", "full_back_server_null_say");
		errMap.put("randomId", 0L);
		return errMap;
	}

	@Override
	public Map sayHello2(HelloRequest hr) {
		log.info("进入服务消费者降级处理");
		Map<String, Object> errMap = new HashMap<String, Object>();
		errMap.put("name", "full_back_server_null_name");
		errMap.put("say", "full_back_server_null_say");
		errMap.put("randomId", 0L);
		return errMap;
	}
}
