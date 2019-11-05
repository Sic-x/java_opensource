package com.xmh.aisell.service;


import com.xmh.aisell.query.BaseQuery;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 基础业务层接口
 * @param <T>
 * @param <ID>
 */
public interface IBaseService<T, ID extends Serializable> {

    /**
     * 提供添加或修改方法
     * @param t
     */
    void save(T t);

    /**
     * 提供删除方法
     * @param id
     */
    void delete(ID id);

    /**
     * 提供查找一个对象方法
     * @param id
     * @return 返回该类型对象
     */
    T findOne(ID id);

    /**
     * 提供查找全部对象方法
     * @return 返回该类型对象的集合
     */
    List<T> findAll();

    //高级查询

    /**
     * 使用query查询
     * @param query
     * @return
     */
    List<T> queryAll(BaseQuery query);

    /**
     * 使用query查询并分页排序
     * @param query
     * @return
     */
    Page<T> queryPage(BaseQuery query);

    /**
     * 使用jpql查询
     * @param jpql
     * @param params
     * @return
     */
    List findByJpql(String jpql,Object... params);


}
