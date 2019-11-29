package com.xmh.java_config._07_import;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtherApplicationConfig {

    @Bean
    public OtherBean otherBean(){
        OtherBean otherBean = new OtherBean();
        return otherBean;
    }
}
