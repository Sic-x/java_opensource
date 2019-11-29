package com.xmh.java_config._03_scan;


        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.xmh.java_config._03_scan",lazyInit = true)
public class ApplicationContextTest {


}
