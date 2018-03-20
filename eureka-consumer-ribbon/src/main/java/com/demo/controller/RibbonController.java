package com.demo.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.protocol.HelloRequest;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/ribbonController")
public class RibbonController {

	@Value("${common.setting.value}")
	private String commonSettingValue;
	
	@Value("${eureka.eureka-consumer-ribbon.value}")
	private String eurekaConsumerRibbonValue;
	
	@Value("${eureka.eureka-cosumer-ribbon.url1}")
	private String url1;
	@Value("${eureka.eureka-cosumer-ribbon.url2}")
	private String url2;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient lbc;

	/**
	 * 自定义消息头消息体 post请求服务提供者demo
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@HystrixCommand(fallbackMethod="helloConsumerFullBack")
	@RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String helloConsumer() {

		ServiceInstance serverInstance = lbc.choose("EUREKA-PRODUCER");
		// 如果没有服务提供者,那么LoadBalancerClient不能够获取实例,将会导致空指针异常,此时将会使得服务降级不可用.
		if (serverInstance != null) {
			log.info("load blance is:" + serverInstance.getHost() + ":" + serverInstance.getPort() + "/" + serverInstance.getServiceId());
		}

		// head
		HttpHeaders headers = new HttpHeaders();
		List<MediaType> accept = new LinkedList<>();
		accept.add(MediaType.APPLICATION_JSON_UTF8);
		headers.setAccept(accept);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		// body
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("name", "alan");
		postParameters.add("say", "configure center get value commonSettingValue:" + commonSettingValue + ",eurekaProducerValue:" + eurekaConsumerRibbonValue);
		postParameters.add("randomId", (new Random()).nextInt() + "");
		// entity
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(postParameters, headers);

		ResponseEntity<Map> response = restTemplate.postForEntity(url1, requestEntity, Map.class);
		return response.getBody().toString();
	}
	
	/**
	 * 服务降级
	 * @return
	 */
	public String helloConsumerFullBack() {
		log.info("server full back");
		Map<String, Object> errMap = new HashMap<String, Object>();
		errMap.put("name", "full_back_server_null_name");
		errMap.put("say", "full_back_server_null_say");
		errMap.put("randomId", 0L);
		return errMap.toString();
	}

	/**
	 * 简化版post请求,直接将对象作为消息体提交
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@HystrixCommand(fallbackMethod="helloConsumer2FullBack")
	@RequestMapping(value = "/ribbon-consumer2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String helloConsumer2() {
		ServiceInstance serverInstance = lbc.choose("EUREKA-PRODUCER");
		// 如果没有服务提供者,那么LoadBalancerClient不能够获取实例,将会导致空指针异常,此时将会使得服务降级不可用.
		if (serverInstance != null) {
			log.info("load blance is:" + serverInstance.getHost() + ":" + serverInstance.getPort() + "/" + serverInstance.getServiceId());
		}

		HelloRequest hr = new HelloRequest();
		hr.setName("alan");
		hr.setSay("configure center get value commonSettingValue:" + commonSettingValue + ",eurekaProducerValue:" + eurekaConsumerRibbonValue);
		hr.setRandomId((new Random()).nextLong());

		Map response = restTemplate.postForObject(url2, hr, Map.class);
		return response.toString();
	}
	
	/**
	 * 服务降级
	 * @return
	 */
	public String helloConsumer2FullBack() {
		log.info("server full back");
		Map<String, Object> errMap = new HashMap<String, Object>();
		errMap.put("name", "full_back_server_null_name");
		errMap.put("say", "full_back_server_null_say");
		errMap.put("randomId", 0L);
		return errMap.toString();
	}
	
	
	/**
	 * 手动模拟异常,触发服务降级,超过一定次数后触发熔断,之后一段时间内即便是正常的访问也会被降级,直到重新检测是否熔断.
	 * 
	 * @return
	 */
	@HystrixCommand(fallbackMethod="hystrixCircuitBreakerFullBack")
	@RequestMapping(value = "/hystrixCircuitBreaker", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String hystrixCircuitBreaker(@RequestParam String triggered) {
		if("true".equals(triggered)){
			throw new RuntimeException("手动抛异常");
		}
		return "正确返回";
	}
	
	/**
	 * 服务降级
	 * @return
	 */
	public String hystrixCircuitBreakerFullBack(@RequestParam String triggered) {
		log.info("server full back");
		Map<String, Object> errMap = new HashMap<String, Object>();
		errMap.put("name", "服务降级处理");
		return errMap.toString();
	}
	
	/**
	 * 模拟限流,性能所限,结果不直观
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	@HystrixCommand(fallbackMethod="hystrixLimitingFullBack")
	@RequestMapping(value = "/hystrixLimiting", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String hystrixLimiting(@RequestParam String triggered) {
		if("true".equals(triggered)){
			//阻塞线程
			try {
				Thread.currentThread().sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return "正确返回";
	}
	
	/**
	 * 服务降级
	 * @return
	 */
	public String hystrixLimitingFullBack(@RequestParam String triggered) {
		log.info("server full back");
		Map<String, Object> errMap = new HashMap<String, Object>();
		errMap.put("name", "服务降级处理");
		return errMap.toString();
	}
}
