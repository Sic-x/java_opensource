package com.xmh.java_config._05_di;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextTest {

    /*@Autowired
    private OtherBean otherBean;*/

   /* @Bean
    public MyBean myBean(){
        MyBean myBean = new MyBean();
        myBean.setUsername("勇勇");
        //myBean.setOtherBean(otherBean());
        //myBean.setOtherBean(otherBean);
        return myBean;
    }*/


   //当spring创建对象的时候发现对象需要传入对象参数,就会先查找【管理】的对象,如果有相同的,就把其传入
    @Bean
    public MyBean myBean(OtherBean otherBean){
        MyBean myBean = new MyBean();
        myBean.setUsername("勇勇");
        myBean.setOtherBean(otherBean);
        return myBean;
    }

    @Bean
    public OtherBean otherBean(){
        OtherBean otherBean = new OtherBean();
        return otherBean;
    }

}
