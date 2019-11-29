package com.xmh.java_config._04_di;

import org.springframework.stereotype.Component;

@Component
public class MyBean {
    private void init() {
        System.out.println("初始化");
    }

    private void destroy() {
        System.out.println("销毁");
    }
}
