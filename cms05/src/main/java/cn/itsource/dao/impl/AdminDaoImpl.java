package cn.itsource.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import cn.itsource.dao.IAdminDao;
import cn.itsource.domain.Admin;

@Repository
public class AdminDaoImpl implements IAdminDao {
	@Autowired
	private JdbcTemplate template;
	
	
	@Override
	public Admin checkName(String username) {
		Admin admin = null;
		try {
			admin = template.queryForObject("select * from admin where username = ?", 
					new BeanPropertyRowMapper<Admin>(Admin.class),username);
		} catch (Exception e) {
			admin = null;
		}
		return admin;
	}

	@Override
	public boolean login(Admin admin) {
		Admin resultAdmin = null;
		try {
			String sql = "SELECT * FROM ADMIN WHERE USERNAME = ? AND PASSWORD = ?";
			resultAdmin = template.queryForObject(sql,new BeanPropertyRowMapper<Admin>(Admin.class),admin.getUsername(),admin.getPassword());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void regsiter(Admin admin) {
		template.update("insert into admin(username,password) values(?,?)",admin.getUsername(),admin.getPassword());
	}

}
