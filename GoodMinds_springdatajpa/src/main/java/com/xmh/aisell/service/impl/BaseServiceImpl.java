package com.xmh.aisell.service.impl;

import com.xmh.aisell.query.BaseQuery;
import com.xmh.aisell.repository.BaseRepository;
import com.xmh.aisell.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * @param <T>
 * @param <ID>
 */
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public abstract class BaseServiceImpl<T,ID extends Serializable> implements IBaseService<T,ID> {

    /**
     * 注入一个BaseRepository实现对象
     * 需注入相应的子接口实现的对象
     */
    @Autowired
    private BaseRepository<T,ID> baseRepository;

    //自动从当前上下文中获取这个对象
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 提供save方法 开启事务 @Transactional
     * @param t 调用该方法的实体类型
     */
    @Override
    @Transactional
    public void save(T t) {
        baseRepository.save(t);
    }

    /**
     * 提供delete方法 开启事务 @Transactional
     * @param id 需要删除的主键
     */
    @Override
    @Transactional
    public void delete(ID id) {
        baseRepository.delete(id);
    }

    /**
     * 提供查找一个对象的方法
     * @param id 需要查询的主键
     * @return 查询到的对象
     */
    @Override
    public T findOne(ID id) {
        return baseRepository.findOne(id);
    }

    /**
     * 提供查找该类所有对象的方法
     * @return 所有对象的list集合
     */
    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    /**
     * 提供根据规则查询的方法
     * @param query
     * @return 所有对象的list集合
     */
    @Override
    public List<T> queryAll(BaseQuery query) {
        Specification spec = query.createSpec();
        return baseRepository.findAll(spec);
    }

    /**
     * 提供根据规则查询的方法并分页排序
     * @param query
     * @return Page对象
     */
    @Override
    public Page<T> queryPage(BaseQuery query) {
        Specification spec = query.createSpec();
        Sort sort = query.createSort();
        Pageable pageable = new PageRequest(query.getJpaPage(),query.getPageSize(),sort);
        Page page = baseRepository.findAll(spec, pageable);
        System.out.println(page);
        return page;
    }

    /**
     * 提供根据jpql查询的方法
     * @param jpql
     * @param params
     * @return 所有对象的list集合
     * list不能用泛型约束,查询出来的对象类型不固定
     */
    @Override
    public List findByJpql(String jpql, Object... params) {
        Query query = entityManager.createQuery(jpql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i+1,params[i]);
        }
        return query.getResultList();
    }
}
