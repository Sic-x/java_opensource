package cn.itsource.domain;

import cn.itsource.dao.IBaseDao;
import cn.itsource.dao.IProductDao;
import cn.itsource.dao.IProductDirDao;
import cn.itsource.dao.impl.BaseDaoImpl;
import cn.itsource.dao.impl.ProductDaoImpl;
import org.junit.Test;
import javax.persistence.*;
import java.util.List;

public class UserTest {

    //EntityManagerFactory重量级对象不用关
    //EntityManager轻量级对象每次执行要关
    @Test
    public void test() throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa01");
        factory.createEntityManager();
    }

    @Test
    public void testAdd() throws Exception {
        User user = new User();
        user.setUsername("yy");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa01");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(user);
        transaction.commit();
        manager.close();
    }


    @Test
    public void testFind() throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa01");
        EntityManager manager = factory.createEntityManager();
        User user = manager.find(User.class, 1L);
        System.out.println(user);
        manager.close();
    }


    @Test
    public void testFindAll() throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa01");
        EntityManager manager = factory.createEntityManager();
        //String jpql = "from User"; //简写
        String jpql = "select u from cn.itsource.domain.User u";
        Query query = manager.createQuery(jpql);
        List<User> list = query.getResultList();
        list.forEach(e->{
            System.out.println(e);
        });
        manager.close();


    }


    @Test
    public void testDel() throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa01");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        User user = manager.find(User.class, 1L);
        transaction.begin();
        manager.remove(user);
        transaction.commit();
        manager.close();
    }

    @Test
    public void testMerge() throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa01");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        User user = new User();
        user.setId(1L);
        user.setUsername("勇勇");
        // 开启事务
        transaction.begin();
        // 持久操作
        manager.merge(user);
        // 提交事务
        transaction.commit();
        // 关闭资源
        manager.close();
    }
    //product测试

    @Test
    public void testProductAdd() throws Exception {
        IProductDao<Product> dao = new ProductDaoImpl<>();
        Product product = new Product("鼠标", 1L);
        dao.save(product);
    }


    @Test
    public void testProductGetOne() throws Exception {
        IProductDao<Product> dao = new ProductDaoImpl<>();
        Product product = dao.getOne(1L);
        System.out.println(product);
    }


    @Test
    public void testProductGetAll() throws Exception {
        IProductDao<Product> dao = new ProductDaoImpl<>();
        List<Product> list = dao.getAll();
        list.forEach(e->{
            System.out.println(e);
        });
    }


    @Test
    public void testProductDel() throws Exception {
        IProductDao<Product> dao = new ProductDaoImpl<>();
        dao.delete(1L);
    }

    @Test
    public void testProductUpdate() throws Exception {
        IProductDao<Product> dao = new ProductDaoImpl<>();
        Product product = new Product(2L, "耳机", 3L);
        dao.update(product);
    }





}