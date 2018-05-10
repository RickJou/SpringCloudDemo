package zrk.test.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:elastic-job/demo-job.xml")
@MapperScan("zrk.test.demo.dao.mapper")
@SpringBootApplication
public class ElasticJobDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticJobDemoApplication.class, args);
	}
	
}
