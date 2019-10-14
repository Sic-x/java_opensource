package cn.itsource.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/* 获取连接对象 */
public class JPAUtil {
    private JPAUtil(){};
    private static EntityManagerFactory factory = null;

    static {
        try {
            // ctral + alt + T
            factory = Persistence.createEntityManagerFactory("jpa01");
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("创建工厂对象失败");
        }
    }

    // 创建EntityManager
    public static EntityManager getManager(){
        return  factory.createEntityManager();
    }
}
