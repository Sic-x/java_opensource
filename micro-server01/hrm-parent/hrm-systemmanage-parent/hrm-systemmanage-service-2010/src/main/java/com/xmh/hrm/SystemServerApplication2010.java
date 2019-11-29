package com.xmh.hrm;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableFeignClients：开启feign的支持
@EnableDiscoveryClient //开启注册中心客户端
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.xmh.hrm.mapper")
public class SystemServerApplication2010 {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(SystemServerApplication2010.class);
    }
}
