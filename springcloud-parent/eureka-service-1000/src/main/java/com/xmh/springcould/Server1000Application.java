package com.xmh.springcould;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//@EnableEurekaServer：开启 注册中心
@SpringBootApplication
@EnableEurekaServer
public class Server1000Application {

	public static void main(String[] args) {
		SpringApplication.run(Server1000Application.class, args);
	}

}
