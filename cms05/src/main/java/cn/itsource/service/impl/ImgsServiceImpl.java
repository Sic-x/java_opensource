package cn.itsource.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.dao.IImgsDao;
import cn.itsource.domain.Imgs;
import cn.itsource.service.IImgsService;
import cn.itsource.util.PageBeanUtil;

@Service
public class ImgsServiceImpl implements IImgsService{
	@Autowired
	private IImgsDao dao;
	@Override
	public PageBeanUtil<Imgs> list(Integer localPage) {
		Integer totalNum = dao.findNum();
		if(localPage==null)
			localPage = 1;
		PageBeanUtil<Imgs> pageBean = new PageBeanUtil<>(localPage, totalNum);
		pageBean.setList(dao.list(pageBean));
		return pageBean;
	}
	@Override
	public void delete(Integer imgid) {
		dao.delete(imgid);
	}
	@Override
	public void add(Imgs imgs) {
		//将信息添加到数据库
		dao.add(imgs);
	}
	@Override
	public Imgs findById(Imgs imgs) {
		return dao.findById(imgs);
	}
	@Override
	public void update(Imgs imgs) {
		dao.update(imgs);
	}
	@Override
	public List<Imgs> indexImgs() {
		return dao.indexImgs();
	}

}
