package com.xmh.springcould;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient//默认是开启 发现客户端
@SpringBootApplication
@EnableCircuitBreaker   //开启熔断
public class OrderServer3000Application {

	public static void main(String[] args) {
		SpringApplication.run(OrderServer3000Application.class, args);
	}

	/**
	 * 方式一
	 * SpringMvc提供的一个基于Rest风格的http调用工具
	 * @return
	 @Bean
	 public RestTemplate restTemplate(){
	 return new RestTemplate();
	 }*/

	/**
	 * SpringMvc提供的一个基于Rest风格的http调用工具
	 * @LoadBalanced ：ribbon的负载均衡标签，赋予RestTemplate有负债均衡的能力
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	//配置负载均衡算法
	@Bean
	public IRule rule(){
		return new RandomRule();
	}

}
