package cn.itsource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.itsource.dao.IAdminDao;
import cn.itsource.domain.Admin;
import cn.itsource.service.IAdminService;
@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private IAdminDao dao;
	
	@Override
	public Admin checkName(String username) {
		return dao.checkName(username);
	}

	@Override
	public boolean login(Admin admin) {
		return dao.login(admin);
	}

	@Override
	public void regsiter(Admin admin) {
		dao.regsiter(admin);
		
	}

}
