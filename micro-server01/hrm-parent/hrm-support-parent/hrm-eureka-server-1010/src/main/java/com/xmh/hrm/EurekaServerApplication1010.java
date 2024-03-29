package com.xmh.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//@EnableEurekaServer：开启注册中心
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication1010 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication1010.class);
    }
}
