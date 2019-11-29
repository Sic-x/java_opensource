package com.xmh.java_config._05_di;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContextTest.class)
public class XmlConfigTest {

    @Autowired
    private MyBean myBean;
    @Autowired
    private OtherBean otherBean;
    @Test
    public void test() throws Exception {
        System.out.println(myBean);
        System.out.println(myBean.getOtherBean());
        System.out.println(otherBean);


    }

    @Test
    public void testSystem() throws Exception {
        System.out.println(System.getProperties());


    }

}
