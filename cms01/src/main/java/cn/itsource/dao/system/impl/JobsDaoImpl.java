package cn.itsource.dao.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.itsource.dao.system.IJobsDao;
import cn.itsource.domain.Jobs;

@Repository
public class JobsDaoImpl implements IJobsDao{
	@Autowired
	private JdbcTemplate template;
	@Override
	public List<Jobs> list() {
		return template.query("SELECT * FROM JOBS", new BeanPropertyRowMapper<Jobs>(Jobs.class));
	}
	@Override
	public void delete(Integer id) {
		template.update("DELETE FROM JOBS WHERE ID = ?", id);
	}

}
