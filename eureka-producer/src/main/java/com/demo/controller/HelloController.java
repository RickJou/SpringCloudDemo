package com.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/helloController")
@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
public class HelloController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@ApiOperation(value = "sayHello", notes = "sayHello http body 参数版") // 使用该注解描述接口方法信息
	@ApiImplicitParams({
			/*
			 * paramType：参数所在输协议中的位置 header-->@RequestHeader
			 * query-->@RequestParam path（用于restful接口）-->@PathVariable body（不常用）
			 * form（不常用）
			 **/
			@ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "say", value = "说点啥", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "randomId", value = "随机数", required = true, dataType = "String", paramType = "query") })
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
	
	@ApiOperation(value = "sayHello", notes = "sayHello对象参数版") // 使用该注解描述接口方法信息
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
