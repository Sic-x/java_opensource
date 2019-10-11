package cn.itsource.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.itsource.dao.IImgsDao;
import cn.itsource.domain.Imgs;
import cn.itsource.domain.Jobs;
import cn.itsource.util.PageBeanUtil;

@Repository
public class ImgsDaoImpl implements IImgsDao{
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public Integer findNum() {
		return template.queryForObject("SELECT COUNT(IMGID) FROM IMGS", Integer.class);
	}

	@Override
	public List<Imgs> list(PageBeanUtil<Imgs> pageBean) {
		String sql = "SELECT * FROM IMGS LIMIT ?,?";
		return template.query(sql, new BeanPropertyRowMapper<Imgs>(Imgs.class),(pageBean.getLocalPage()-1)*pageBean.getPageSize(),pageBean.getPageSize());
	}

	@Override
	public void delete(Integer imgid) {
		template.update("DELETE FROM IMGS WHERE IMGID = ?", imgid);
	}

	@Override
	public void add(Imgs imgs) {
		String sql= "INSERT INTO IMGS(STOREPATH,STORENAME,INTRO,ISENABLED,INPUTDATE) VALUES(?,?,?,?,?)";
		template.update(sql,imgs.getStorepath(),imgs.getStorename(),
				imgs.getIntro(),imgs.getIsenabled(),imgs.getInputdate());
	}

	@Override
	public Imgs findById(Imgs imgs) {
		return template.queryForObject("SELECT * FROM IMGS WHERE IMGID = ?", new BeanPropertyRowMapper<Imgs>(Imgs.class),imgs.getImgid());
	}

	@Override
	public void update(Imgs imgs) {
		String sql = "UPDATE IMGS SET ISENABLED = ?,INTRO = ? WHERE IMGID = ?";
		template.update(sql, imgs.getIsenabled(),imgs.getIntro(),imgs.getImgid());
	}

	@Override
	public List<Imgs> indexImgs() {
		return template.query("SELECT * FROM IMGS WHERE ISENABLED = 1", new BeanPropertyRowMapper<Imgs>(Imgs.class));
	}
	

}
