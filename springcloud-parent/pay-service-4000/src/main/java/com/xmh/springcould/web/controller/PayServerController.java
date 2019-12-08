package com.xmh.springcould.web.controller;


import com.xmh.springcloud.domain.User;
import com.xmh.springcould.feignclient.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayServerController {

    @Autowired
    private UserFeignClient userFeignClient ;

    //浏览器调用
    @RequestMapping("/getById/{id}")
    public User getUserById(@PathVariable("id")Long id){
        return userFeignClient.getById(id);
    }
}
