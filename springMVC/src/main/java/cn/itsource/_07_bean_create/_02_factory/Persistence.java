package cn.itsource._07_bean_create._02_factory;

//通过持久化方法创建工厂对象
public class Persistence {
    public static EntityManagerFactory createEntityManagerFactory() {
        return new EntityManagerFactory();
    }
}
