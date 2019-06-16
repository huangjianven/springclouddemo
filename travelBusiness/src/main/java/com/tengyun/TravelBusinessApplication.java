package com.tengyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("com.tengyun.**.dao")
@EnableEurekaClient
@SpringBootApplication
public class TravelBusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelBusinessApplication.class, args);
	}

}
