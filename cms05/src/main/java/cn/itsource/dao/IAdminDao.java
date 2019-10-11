package cn.itsource.dao;

import cn.itsource.domain.Admin;

public interface IAdminDao {

	Admin checkName(String username);

	boolean login(Admin admin);

	void regsiter(Admin admin);

}
