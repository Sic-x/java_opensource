package cn.itsource.dao;

import cn.itsource.model.User;

public interface BaseDao {
	Boolean add(User user);
	void delete(User user);
	User query(String name);
	void update(User user);
}
