package com.xmh.springcould;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;


//@EnableConfigServer :开启配置中心服务
@EnableDiscoveryClient //开启注册中心客户端
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication7000 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication7000.class);
    }

}
