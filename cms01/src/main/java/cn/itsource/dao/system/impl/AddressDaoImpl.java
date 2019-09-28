package cn.itsource.dao.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.itsource.dao.system.IAddressDao;
import cn.itsource.domain.Address;

@Repository
public class AddressDaoImpl implements IAddressDao {
	@Autowired
	private JdbcTemplate template;
	@Override
	public List<Address> list() {
		return template.query("SELECT * FROM ADDRESS", new BeanPropertyRowMapper<Address>(Address.class));
	}

}
