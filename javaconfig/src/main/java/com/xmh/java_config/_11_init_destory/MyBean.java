package com.xmh.java_config._11_init_destory;

        import org.springframework.beans.factory.DisposableBean;
        import org.springframework.beans.factory.InitializingBean;

public class MyBean implements InitializingBean,DisposableBean{
    public void destroy() throws Exception {
        System.out.println("死了");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("设置");
    }
}
