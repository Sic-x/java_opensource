package com.xmh.java_config._06_condition;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class XmlConfigTest {

    @Autowired
    private MyBean myBean;

    @Test
    public void test() throws Exception {
        System.out.println(myBean);
    }



}