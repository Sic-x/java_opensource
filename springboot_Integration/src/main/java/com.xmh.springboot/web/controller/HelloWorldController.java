package com.xmh.springboot.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
@Slf4j
public class HelloWorldController {

    //private Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private DataSource dataSource ;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection.toString());
        log.info("13213213");
        log.debug(connection.toString());
        connection.close();
        return "Hello,world";
    }


    @RequestMapping("/index")
    public String thymeleaf(Model model){
        model.addAttribute("username","admin");
        return "hello-thymeleaf";
    }
}

