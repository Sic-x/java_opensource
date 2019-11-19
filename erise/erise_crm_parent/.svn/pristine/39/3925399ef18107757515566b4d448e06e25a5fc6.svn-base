package com.xmh.basic.mapper;

import com.xmh.basic.query.BaseQuery;

import java.util.List;

public interface BaseMapper<T> {
    List<T> findAll();

    T findOne(Long id);

    void save(T t);

    void update(T t);

    void remove(Long id);
    //----------------------------------------------------------

    /**
     * 查询分页总数
     * @param query
     * @return
     */
    Long querryCount(BaseQuery query);

    /**
     * 查询当前页数据
     * @param query
     * @return
     */
    List<T> queryData(BaseQuery query);


}
