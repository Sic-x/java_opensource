package cn.itsource.service;

import cn.itsource.domain.Admin;

public interface IAdminService {

	Admin checkName(String username);

	boolean login(Admin admin);

	void regsiter(Admin admin);

}
