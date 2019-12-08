package com.xmh.springcould.web.controller;

import com.xmh.springcloud.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务，提供user
 */
@RestController
@RequestMapping("/user")
public class UserServerController {

    @GetMapping("/getById/{id}")
    public User getById(@PathVariable("id")Long id){
        return new User(id,"zs:"+id,"2001端口");
    }

}
