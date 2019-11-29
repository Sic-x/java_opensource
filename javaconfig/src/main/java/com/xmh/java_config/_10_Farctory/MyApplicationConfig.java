package com.xmh.java_config._10_Farctory;



import com.xmh.java_config._09_DefinitionRegistry.MyDefinitionRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration

public class MyApplicationConfig {

    /**
     * @return 返回由工厂方式定义的myBean
     */
    @Bean
    public MyFactoryBean myBean(){
        return new MyFactoryBean();
    }
}
