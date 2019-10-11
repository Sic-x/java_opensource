package cn.itsource.service;

import java.util.List;

import cn.itsource.domain.Imgs;
import cn.itsource.util.PageBeanUtil;

public interface IImgsService {

	PageBeanUtil<Imgs> list(Integer localPage);

	void delete(Integer imgid);

	void add(Imgs imgs);

	Imgs findById(Imgs imgs);

	void update(Imgs imgs);

	List<Imgs> indexImgs();

}
