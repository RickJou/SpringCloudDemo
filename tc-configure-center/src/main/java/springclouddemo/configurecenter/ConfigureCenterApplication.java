package springclouddemo.configurecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
//@EnableDiscoveryClient
@SpringBootApplication
public class ConfigureCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigureCenterApplication.class, args);
	}
}
