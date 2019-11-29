package com.xmh.java_config._02_java_config_ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextTest {


    @Bean
    public MyBean myBean(){
        MyBean myBean = new MyBean();
        return myBean;
    }
}
