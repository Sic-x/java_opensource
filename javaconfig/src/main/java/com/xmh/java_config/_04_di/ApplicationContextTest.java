package com.xmh.java_config._04_di;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationContextTest {

    @Bean(name = "myB",initMethod = "init",destroyMethod = "destroy")
    //@Scope(scopeName = "prototype/singleton")
    public MyBean myBean(){
        MyBean myBean = new MyBean();
        return myBean;
    }

}
