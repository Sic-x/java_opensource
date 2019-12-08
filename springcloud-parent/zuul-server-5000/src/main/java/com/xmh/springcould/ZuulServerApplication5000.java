package com.xmh.springcould;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//@EnableZuulProxy:开启zuul网关
@EnableDiscoveryClient //开启注册中心客户端
@SpringBootApplication
@EnableZuulProxy
public class ZuulServerApplication5000 {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication5000.class);
    }
}
