package com.xmh.springboot02.user_properties;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "users")
@Data
public class UserConfig {

    @Value("${users.id}")
    private String id;
    @Value("${users.name}")
    private String name;
    @Value("${users.age}")
    private String age;
}
