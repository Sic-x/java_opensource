package com.xmh.java_config._10_Farctory;

        import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<MyBean>{
    public MyBean getObject() throws Exception {
        return new MyBean();
    }

    public Class<?> getObjectType() {
        return MyBean.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
