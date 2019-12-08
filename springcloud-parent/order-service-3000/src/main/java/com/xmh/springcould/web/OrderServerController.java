package com.xmh.springcould.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xmh.springcloud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 订单服务
 */
@RestController
@RequestMapping("/order")

public class OrderServerController {
 
    @Autowired
    private RestTemplate restTemplate;
 
    /*//浏览器调用
    //方法一：restTemplate
    @RequestMapping("/getById/{id}")
    public User getUserById(@PathVariable("id")Long id){
        //远程调用UserServer服务获取User对象： RestTemplate
        String url = "http://localhost:2000/user/getById/"+id;
        return restTemplate.getForObject(url, User.class);
    }*/

    /*//方式二：访问服务名
    @GetMapping("/getById/{id}")
    public User getUserById(@PathVariable("id") Long id){
        //String url = "http://localhost:2000/user/"+id;
        String url = "http://user-server/user/getById/"+id;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }*/


    //浏览器调用
    //HystrixCommand:对方法做熔断，如果方法中出现异常，会走托底方法fallbackMethod = "getUserByIdFallback"
    @HystrixCommand(fallbackMethod = "getUserByIdFallback")
    @RequestMapping("/getById/{id}")
    public User getUserById(@PathVariable("id")Long id){
        //远程调用UserServer服务获取User对象： RestTemplate
        //String url = "http://localhost:2000/user/getById/"+id;
        String url = "http://user-server/user/getById/"+id;
        return restTemplate.getForObject(url, User.class);
    }

    //托底方法
    public User getUserByIdFallback(@PathVariable("id")Long id){
        return new User(-1L,"无效的用户","用户服务不可用....");
    }
}