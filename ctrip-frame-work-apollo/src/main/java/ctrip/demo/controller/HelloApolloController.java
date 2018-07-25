package ctrip.demo.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

@RestController
@RequestMapping("/helloController")
public class HelloApolloController {
	
	@Value("${hello.apollo.testValue1:dafaultVlaue}")
	private String testValue1;
	@Value("${hello.apollo.testValue2:dafaultVlaue}")
	private String testValue2;
	@Value("${hello.apollo.testValue3:dafaultVlaue}")
	private String testValue3;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/sayHello", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Map sayHello() {
		Map map = new HashMap();
		map.put("hello.apollo.testValue1", testValue1);
		map.put("hello.apollo.testValue2", testValue2);
		map.put("hello.apollo.testValue3", testValue3);
		return map;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/sayAllConfig/{namespace1}/{namespace2}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Map sayAllConfig(@PathVariable("namespace1") String namespace1,@PathVariable("namespace2") String namespace2) {
		//http://127.0.0.1:8080/helloController/sayAllConfig/test-project/dev/application/org1.public-namespace
		
		Config privateConfig1 = ConfigService.getConfig(namespace1); 
		Set<String> names1 = privateConfig1.getPropertyNames();
		
		Config privateConfig2 = ConfigService.getConfig(namespace2); 
		Set<String> names2 = privateConfig2.getPropertyNames();
		
		Map map = new HashMap();
		for (String key : names1) {
			map.put(key, privateConfig1.getProperty(key, "没有找到值!"));
		}
		for (String key : names2) {
			map.put(key, privateConfig2.getProperty(key, "没有找到值!"));
		}
		return map;
	}
	
}
