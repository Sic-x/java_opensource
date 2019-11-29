package com.xmh.java_config._06_condition;

import org.springframework.stereotype.Component;

@Component
public class MyBean {

    private String system;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        system = system;
    }

    private void init() {
        System.out.println("初始化");
    }

    private void destroy() {
        System.out.println("销毁");
    }
}
