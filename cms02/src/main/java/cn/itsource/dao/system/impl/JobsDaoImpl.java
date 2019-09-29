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
		String sql = "SELECT * FROM VIEW_JOBS_CITY";
		return template.query(sql, new BeanPropertyRowMapper<Jobs>(Jobs.class));
	}
	
	@Override
	public void delete(Integer id) {
		template.update("DELETE FROM JOBS WHERE ID = ?", id);
	}
	
	@Override
	public void add(Jobs jobs) {
		String sql = "INSERT INTO JOBS (TITLE,CID,JOBNUM,TREATMENT,DESCRIBES,REQUIRES,POSITIONTYPE,ISENABLED,INPUTDATE) VALUES(?,?,?,?,?,?,?,?,?)";
		template.update(sql,jobs.getTitle(),jobs.getCid(),jobs.getJobnum(),jobs.getTreatment(),jobs.getDescribes(),jobs.getRequires(),
				jobs.getPositiontype(),jobs.getIsenabled(),jobs.getInputdate());	
	}
	
	@Override
	public Jobs findById(Jobs jobs) {
		return template.queryForObject("SELECT * FROM JOBS WHERE ID = ?", new BeanPropertyRowMapper<Jobs>(Jobs.class),jobs.getId());
	}

	@Override
	public void update(Jobs jobs) {
		String sql = "UPDATE JOBS SET TITLE = ?,CID = ?,JOBNUM = ?,TREATMENT = ?,DESCRIBES = ?,REQUIRES = ?,POSITIONTYPE = ?,ISENABLED = ?,INPUTDATE = ? WHERE ID = ?";
		template.update(sql, jobs.getTitle(),jobs.getCid(),jobs.getJobnum(),jobs.getTreatment(),jobs.getDescribes(),jobs.getRequires(),
				jobs.getPositiontype(),jobs.getIsenabled(),jobs.getInputdate(),jobs.getId());
	}

}
