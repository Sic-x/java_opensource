package com.xmh.java_config._04_di;


        import org.junit.Test;
        import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class XmlConfigTest {


    @Test
    public void test() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextTest.class);
        MyBean bean = context.getBean(MyBean.class);
        System.out.println(bean);
    }

}
