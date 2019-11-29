package com.xmh.java_config._07_import;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OtherApplicationConfig.class,OtherBean.class})
public class MyApplicationConfig {

    @Bean
    public MyBean myBean(OtherBean otherBean){
        MyBean myBean = new MyBean();
        myBean.setOtherBean(otherBean);
        return myBean;
    }

}
