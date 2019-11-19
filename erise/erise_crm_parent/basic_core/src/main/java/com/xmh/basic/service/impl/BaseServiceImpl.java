package com.xmh.basic.service.impl;


import com.xmh.basic.mapper.BaseMapper;
import com.xmh.basic.query.BaseQuery;
import com.xmh.basic.service.BaseService;
import com.xmh.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;


    @Override
    public List<T> findAll() {
        return baseMapper.findAll();
    }

    @Override
    public T findOne(Long id) {
        return baseMapper.findOne(id);
    }

    @Override
    @Transactional
    public void save(T t) {
        baseMapper.save(t);
    }

    @Override
    @Transactional
    public void update(T t) {
        baseMapper.update(t);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        baseMapper.remove(id);
    }

    //----------------------------------------------------------

    /**
     * 分页方法的实现
     * @param query
     * @return
     */
    @Override
    public PageList<T> query(BaseQuery query) {
        //  通过Mapper查询总数
        Long total = baseMapper.querryCount(query);
        if (total<1){
            return new PageList<>();
        }
        //查询当前页数据
        List<T> rows = baseMapper.queryData(query);
        return new PageList<>(total,rows);

    }
}
