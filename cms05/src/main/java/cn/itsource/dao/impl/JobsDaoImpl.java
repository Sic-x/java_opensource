package cn.itsource.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.itsource.dao.IJobsDao;
import cn.itsource.domain.Jobs;
import cn.itsource.util.PageBeanUtil;

@Repository
public class JobsDaoImpl implements IJobsDao{
	
	@Autowired
	private JdbcTemplate template;
	
	/*@Override
	public List<Jobs> list() {
		String sql = "SELECT * FROM VIEW_JOBS_CITY";
		return template.query(sql, new BeanPropertyRowMapper<Jobs>(Jobs.class));
	}*/
	
	@Override
	public void delete(Integer id) {
		template.update("DELETE FROM JOBS WHERE ID = ?", id);
	}
	
	@Override
	public void add(Jobs jobs) {
		String sql = "INSERT INTO JOBS (TITLE,CID,JOBNUM,TREATMENT,DESCRIBES,REQUIRES,POSITIONTYPE,ISENABLED,INPUTDATE,HTMLURL) VALUES(?,?,?,?,?,?,?,?,?,?)";
		template.update(sql,jobs.getTitle(),jobs.getCid(),jobs.getJobnum(),jobs.getTreatment(),jobs.getDescribes(),jobs.getRequires(),
				jobs.getPositiontype(),jobs.getIsenabled(),jobs.getInputdate(),jobs.getHtmlurl());	
	}
	
	@Override
	public Jobs findById(Jobs jobs) {
		return template.queryForObject("SELECT * FROM JOBS WHERE ID = ?", new BeanPropertyRowMapper<Jobs>(Jobs.class),jobs.getId());
	}

	@Override
	public void update(Jobs jobs) {
		String sql = "UPDATE JOBS SET TITLE = ?,CID = ?,JOBNUM = ?,TREATMENT = ?,DESCRIBES = ?,REQUIRES = ?,POSITIONTYPE = ?,ISENABLED = ?,INPUTDATE = ?,HTMLURL = ? WHERE ID = ?";
		template.update(sql, jobs.getTitle(),jobs.getCid(),jobs.getJobnum(),jobs.getTreatment(),jobs.getDescribes(),jobs.getRequires(),
				jobs.getPositiontype(),jobs.getIsenabled(),jobs.getInputdate(),jobs.getHtmlurl(),jobs.getId());
	}

	@Override
	public Integer findNum() {
		return template.queryForObject("SELECT COUNT(ID) FROM JOBS", Integer.class);
	}

	@Override
	public List<Jobs> list(PageBeanUtil<Jobs> pageBean) {
		String sql = "SELECT * FROM VIEW_JOBS_CITY LIMIT ?,?";
		return template.query(sql, new BeanPropertyRowMapper<Jobs>(Jobs.class),(pageBean.getLocalPage()-1)*pageBean.getPageSize(),pageBean.getPageSize());
	}

	@Override
	public Integer indexFindNum() {
		return template.queryForObject("SELECT COUNT(ID) FROM JOBS WHERE ISENABLED = 1", Integer.class);
	}

	@Override
	public List<Jobs> indexList(PageBeanUtil<Jobs> pageBean) {
		String sql = "SELECT * FROM VIEW_JOBS_CITY WHERE ISENABLED = 1 LIMIT ?,? ";
		return template.query(sql, new BeanPropertyRowMapper<Jobs>(Jobs.class),(pageBean.getLocalPage()-1)*pageBean.getPageSize(),pageBean.getPageSize());
	}

	@Override
	public Integer indexFindNum(String sql) {
		return template.queryForObject("SELECT COUNT(ID) FROM JOBS WHERE ISENABLED = 1 "+sql, Integer.class);
	}

	@Override
	public List<Jobs> indexList(PageBeanUtil<Jobs> pageBean, String sql) {
		sql = "SELECT * FROM VIEW_JOBS_CITY WHERE ISENABLED = 1 "+sql+" LIMIT ?,? ";
		return template.query(sql, new BeanPropertyRowMapper<Jobs>(Jobs.class),(pageBean.getLocalPage()-1)*pageBean.getPageSize(),pageBean.getPageSize());
	}

}
