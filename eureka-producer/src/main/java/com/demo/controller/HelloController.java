package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.protocol.HelloRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/helloController")
public class HelloController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	@RequestMapping(value = "/sayHello", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Map sayHello(@RequestParam String name, @RequestParam String say, @RequestParam String randomId) {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		log.info("discovery client output:" + instance.getHost() + ":" + instance.getPort() + "/" + instance.getServiceId());

		log.info("进入服务生产者");
		Map map = new HashMap<String, String>();
		map.put("name", name);
		map.put("say", say);
		map.put("randomId", randomId);
		log.info("服务生产者返回数据");
		return map;
	}

	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	@RequestMapping(value = "/sayHello2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Map sayHello2(@RequestBody HelloRequest hr) {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		log.info("discovery client output:" + instance.getHost() + ":" + instance.getPort() + "/" + instance.getServiceId());

		log.info("进入服务生产者");
		Map map = new HashMap<String, String>();
		map.put("name", hr.getName());
		map.put("say", hr.getSay());
		map.put("randomId", hr.getRandomId());
		log.info("服务生产者返回数据");
		return map;
	}

}
