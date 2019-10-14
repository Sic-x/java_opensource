package cn.itsource.dao.impl;

import cn.itsource.dao.IBaseDao;
import cn.itsource.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDaoImpl<T> implements IBaseDao<T>{

    Class<T> cls = null;

    BaseDaoImpl(){
        cls = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void save(T t) {
        EntityManager manager = JPAUtil.getManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(t);
        transaction.commit();
        manager.close();

    }

    @Override
    public void update(T t) {
        EntityManager manager = JPAUtil.getManager();
        EntityTransaction transaction = manager.getTransaction();
        // 开启事务
        transaction.begin();
        // 持久操作
        manager.merge(t);
        // 提交事务
        transaction.commit();
        // 关闭资源
        manager.close();
    }

    @Override
    public void delete(Serializable id) {
        EntityManager manager = JPAUtil.getManager();
        EntityTransaction transaction = manager.getTransaction();
        T t = manager.find(cls, id);
        transaction.begin();
        manager.remove(t);
        transaction.commit();
        manager.close();
    }

    @Override
    public List<T> getAll() {
        EntityManager manager = JPAUtil.getManager();
        String name = cls.getSimpleName();
        String jpql = "select u from cn.itsource.domain." + name + " u";
        Query query = manager.createQuery(jpql);
        List<T> list = query.getResultList();
        manager.close();
        return list;
    }

    @Override
    public T getOne(Serializable id) {
        EntityManager manager = JPAUtil.getManager();
        T t = manager.find(cls, id);
        manager.close();
        return t;
    }
}
