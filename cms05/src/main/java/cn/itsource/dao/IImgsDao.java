package cn.itsource.dao;

import java.util.List;

import cn.itsource.domain.Imgs;
import cn.itsource.util.PageBeanUtil;

public interface IImgsDao {

	Integer findNum();

	List<Imgs> list(PageBeanUtil<Imgs> pageBean);

	void delete(Integer imgid);

	void add(Imgs imgs);

	Imgs findById(Imgs imgs);

	void update(Imgs imgs);

	List<Imgs> indexImgs();

}
