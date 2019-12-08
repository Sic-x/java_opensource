package com.xmh.springcould;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableFeignClients：开启feign的支持
@EnableDiscoveryClient //开启注册中心客户端
@SpringBootApplication
@EnableFeignClients("com.xmh.springcould.feignclient")//默认全扫描
public class PayServerApplication4000 {

    public static void main(String[] args) {
        SpringApplication.run(PayServerApplication4000.class);
    }
}
