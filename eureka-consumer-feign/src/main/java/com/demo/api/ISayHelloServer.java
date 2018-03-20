package com.demo.api;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.protocol.HelloRequest;

@FeignClient(value = "eureka-producer",fallback = SayHelloServerFullBack.class)
@SuppressWarnings("rawtypes")
public interface ISayHelloServer {
    
	/**
	 * 注意,定义此接口时,@RequestParam("xxx")和@ReqeustHead("xxx")必须写value,
	 * 不同于springmvc自动根据参数名作为默认值,feign必须指定这个参数对应哪个服务提供端的参数
	 * @param name
	 * @param say
	 * @param randomId
	 * @return
	 */
	@RequestMapping(value = "/helloController/sayHello",method = RequestMethod.POST)
    Map sayHello(@RequestParam("name") String name, @RequestParam("say") String say, @RequestParam("randomId") String randomId);
    
    @RequestMapping(value = "/helloController/sayHello2",method = RequestMethod.POST)
    Map sayHello2(@RequestBody HelloRequest hr);
}
