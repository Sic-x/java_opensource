package com.xmh.java_config._06_condition;


        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Conditional;
        import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {


    @Bean
    @Conditional(value = WindowsCondition.class)
    public MyBean windowsMyBean(){
        MyBean myBean = new MyBean();
        myBean.setSystem("windows");
        return myBean;
    }

    @Bean
    @Conditional(value = LinuxCondition.class)
    public MyBean linuxMyBean(){
        MyBean myBean = new MyBean();
        myBean.setSystem("Linux");
        return myBean;
    }


}
