package cn.itsource.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.itsource.dao.ICityDao;
import cn.itsource.domain.City;

@Repository
public class CityDaoImpl implements ICityDao {
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<City> list() {
		return template.query("SELECT * FROM CITY ORDER BY ID", new BeanPropertyRowMapper<City>(City.class));
	}

}
