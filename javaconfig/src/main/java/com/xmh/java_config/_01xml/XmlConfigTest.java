package com.xmh.java_config._01xml;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlConfigTest {


    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyBean bean = context.getBean(MyBean.class);
        System.out.println(bean);
    }

}
