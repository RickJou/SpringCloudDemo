package ctrip.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@SpringBootApplication
@EnableApolloConfig(value = {"application"})
public class ApolloDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApolloDemoApplication.class, args);
	}
}
