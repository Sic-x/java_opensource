package cn.itsource._07_bean_create._02_factory;


import org.springframework.beans.factory.FactoryBean;

//实现FactoryBean接口,每个FactoryBean对象都会去管理创建的对象
public class EntityManagerFactoryBean implements FactoryBean<EntityManagerFactory>{

    //最后返回的配置对象
    @Override
    public EntityManagerFactory getObject() throws Exception {
        return Persistence.createEntityManagerFactory();
    }

    //返回的类型
    @Override
    public Class<?> getObjectType() {
        return EntityManagerFactory.class;
    }

    //确认是否是单例模式
    @Override
    public boolean isSingleton() {
        return true;
    }
}
