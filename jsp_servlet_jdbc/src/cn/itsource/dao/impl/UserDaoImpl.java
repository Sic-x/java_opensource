package cn.itsource.dao.impl;

import java.util.List;

import org.omg.CORBA.Request;

import cn.itsource.dao.IUserDao;
import cn.itsource.model.User;
import cn.itsource.util.JDBCUtil;

public class UserDaoImpl implements IUserDao {

	@Override
	public Boolean add(User user) {
		User queryUser = null;
		queryUser = query(user.getUsername());
		if (queryUser==null) {
			Object[] objs = {user.getUsername(),user.getPassword(),
					user.getNickname()};
			String sql = "INSERT INTO USER(USERNAME,PASSWORD,NICKNAME)VALUES(?,?,?)";
			JDBCUtil.executeSql(sql, objs);
			return true;
		}
		return false;
	}

	@Override
	public void delete(User user) {
		Object objs = user.getUsername();
		String sql = "DELETE FROM USER WHERE USERNAME = ?";
		JDBCUtil.executeSql(sql, objs);
	}
	//查询一个
	@Override
	public User query(String name) {	
		String sql = "SELECT * FROM USER WHERE USERNAME = ?";
		User result = JDBCUtil.getSingleResult(sql, User.class, name);
		return	result;
	}
	//重载
	@Override
	public User query(User user) {
		Object[] objs = {user.getUsername(),user.getPassword()};
		String sql = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
		User result = JDBCUtil.getSingleResult(sql, User.class, objs);
		return	result;
	}
	//重载
	@Override
	public List<User> query() {
		Object objs = " ";
		String sql = "SELECT * FROM USER WHERE 1=1 OR USERNAME = ?";
		List<User> list = JDBCUtil.getResult(sql, User.class, objs);
		return	list;
	}

	@Override
	public void update(User user) {
		Object[] objs = {user.getPassword(),
				user.getNickname(),user.getUsername()};
		String sql = "UPDATE USER SET PASSWORD = ?, NICKNAME = ? WHERE USERNAME = ?";
		JDBCUtil.executeSql(sql, objs);
	}

	@Override
	public List<User> querylike(String name) {
		String sql = "SELECT * FROM USER WHERE USERNAME LIKE '%?%'";
		List<User> result = JDBCUtil.getResult(sql, User.class, name);
		return result;
	}

	@Override
	public Boolean login(User user) {
		User result = null;
		result = query(user);
		if(result!=null){
			return true;
		}
		return false;		
	}

	@Override
	public Boolean regist(User user) {
		Boolean flag = add(user);
		return flag;	
	}

	

	

}
