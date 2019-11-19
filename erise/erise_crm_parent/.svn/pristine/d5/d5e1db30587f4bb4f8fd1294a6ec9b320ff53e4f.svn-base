package com.xmh.basic.service;

import com.xmh.basic.query.BaseQuery;
import com.xmh.util.PageList;

import java.util.List;

public interface BaseService<T> {

    List<T> findAll();

    T findOne(Long id);

    void save(T t);

    void update(T t);

    void remove(Long id);

    /**
     * 分页+高级列表
     * @param query
     * @return
     */
    PageList<T> query(BaseQuery query);


}
