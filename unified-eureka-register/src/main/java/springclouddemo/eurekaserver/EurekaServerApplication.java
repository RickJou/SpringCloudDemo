package springclouddemo.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

	/**
	 * 默认情况下,使用application-single.properties配置文件,提供单点注册中心
	 * 如果java run命令后指定了环境,则使用指定的环境启动
	 * @param args
	 */
	public static void main(String[] args) {
		boolean hasProfile = false;
		for (int i=0;i<args.length;i++) {
			String arg = args[i];
			if(arg!=null&&arg.indexOf("spring.profiles.active")!=-1){
				hasProfile = true;
			}
		}
		if(!hasProfile){
			List<String> argList = new ArrayList<String>();
			if(args!=null&&args.length>0){
				argList = Arrays.asList(args);
			}
			argList.add("--spring.profiles.active=single");
			args = argList.toArray(args);
		}
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
