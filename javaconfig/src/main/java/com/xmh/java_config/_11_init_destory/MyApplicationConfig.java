package com.xmh.java_config._11_init_destory;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyApplicationConfig {

    @Bean
    public MyBean myBean(){
        return new MyBean();
    }

}
