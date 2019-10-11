package cn.itsource.dao;

import java.util.List;

import cn.itsource.model.User;

public interface IUserDao extends BaseDao{
	List<User> querylike(String name);
	User query(User user);
	List<User> query();
	Boolean login(User user);
	Boolean regist(User user);
}
