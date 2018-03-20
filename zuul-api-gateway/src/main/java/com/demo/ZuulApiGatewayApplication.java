package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.demo.filter.ErrorFilter;
import com.demo.filter.PostFilter;
import com.demo.filter.PreFilter;


@EnableZuulProxy
//@EnableDiscoveryClient
@SpringBootApplication
public class ZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiGatewayApplication.class, args);
	}

	@Bean
	public PreFilter preFilter(){
		return new PreFilter();
	}
	
	@Bean
	public PostFilter postFilter(){
		return new PostFilter();
	}
	
	@Bean
	public ErrorFilter errorFilter(){
		return new ErrorFilter();
	}
}
