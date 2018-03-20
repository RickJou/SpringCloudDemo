package com.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.api.ISayHelloServer;
import com.demo.protocol.HelloRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/feignController")
public class FeignController {

	@Value("${common.setting.value}")
	private String commonSettingValue;
	@Value("${eureka.eureka-consumer-feign.value}")
	private String eurekaConsulmerFeignValue;
	@Autowired
	private LoadBalancerClient lbc;

	@Autowired
	private ISayHelloServer sayHelloServer;

	@RequestMapping(value = "/feign-consumer", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String helloConsumer(RestTemplate restTemplate) {
		log.info("feign consumer1 ......");
		ServiceInstance serverInstance = lbc.choose("EUREKA-PRODUCER");
		// 如果没有服务提供者,那么LoadBalancerClient不能够获取实例,将会导致空指针异常,此时将会使得服务降级不可用.
		if (serverInstance != null) {
			log.info("load blance is:" + serverInstance.getHost() + ":" + serverInstance.getPort() + "/" + serverInstance.getServiceId());
		}

		return sayHelloServer.sayHello("alan", "Hello Feign Client!"+"configure center get value commonSettingValue:" + commonSettingValue + ",eurekaProducerValue:" + eurekaConsulmerFeignValue, (new Random()).nextLong() + "").toString();
	}

	@RequestMapping(value = "/feign-consumer2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String helloConsumer2(RestTemplate restTemplate) {
		log.info("feign cosumer2 ......");
		ServiceInstance serverInstance = lbc.choose("EUREKA-PRODUCER");
		// 如果没有服务提供者,那么LoadBalancerClient不能够获取实例,将会导致空指针异常,此时将会使得服务降级不可用.
		if (serverInstance != null) {
			log.info("load blance is:" + serverInstance.getHost() + ":" + serverInstance.getPort() + "/" + serverInstance.getServiceId());
		}
		
		HelloRequest hr = new HelloRequest();
		hr.setName("alan");
		hr.setSay("configure center get value commonSettingValue:" + commonSettingValue + ",eurekaProducerValue:" + eurekaConsulmerFeignValue);
		hr.setRandomId((new Random()).nextLong());

		return sayHelloServer.sayHello2(hr).toString();
	}
}
