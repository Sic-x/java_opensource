package com.xmh.java_config._12_postconstract_predestory;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MyBean {

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("死了");
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("创建");
    }
}
