package com.tc.swaggerui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/indexController")
public class IndexController {
	@Autowired
	private DiscoveryClient discoveryClient;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/showAllServerForEureka", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ModelAndView showAllServerForEureka(ModelAndView mv) {
		log.info("测试获取所有服务列表");

		List<ServiceInstance> temp = new ArrayList<>();

		List<String> allServiceId = discoveryClient.getServices();
		for (String serviceId : allServiceId) {
			List<ServiceInstance> list = discoveryClient.getInstances(serviceId);
			if (list != null && !list.isEmpty()) {
				temp.addAll(list);
			}
		}
		
		//获取所有的服务和所在的地址
		Map nameAndUri = new TreeMap<>();
		for (ServiceInstance serviceInstance : temp) {
			if(!nameAndUri.containsKey(serviceInstance.getServiceId().toString())){
				nameAndUri.put(serviceInstance.getServiceId().toString(), new ArrayList<>());
			}
			ArrayList list = (ArrayList) nameAndUri.get(serviceInstance.getServiceId().toString());
			list.add(serviceInstance.getUri().toString());
		}
		
		mv.setViewName("swaggerui-eureka");
		mv.addObject("allService", nameAndUri);
		return mv;
	}

}
