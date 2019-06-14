package com.tengyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.tengyun.**.dao")
@ComponentScan("com.tengyun.**")
@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class TravelBusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelBusinessApplication.class, args);
	}

}
