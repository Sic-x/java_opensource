package cn.itsource._04_autoDI.controller;

import cn.itsource._04_autoDI.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private IUserService service;

    public void save(){
        System.out.println("controller");
        service.save();
    }
}
