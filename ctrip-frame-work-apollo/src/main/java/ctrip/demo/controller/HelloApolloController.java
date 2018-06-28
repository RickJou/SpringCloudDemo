package ctrip.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
}
