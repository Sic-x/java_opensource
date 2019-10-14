package cn.itsource.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    void save(T t);
    void update(T t);
    void delete(Serializable id);
    List<T> getAll();
    T getOne(Serializable id);
}
