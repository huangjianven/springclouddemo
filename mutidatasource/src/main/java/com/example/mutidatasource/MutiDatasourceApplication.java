package com.example.mutidatasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.example.**.dao")
@ComponentScan("com.example.**")
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class MutiDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutiDatasourceApplication.class, args);
	}

}
