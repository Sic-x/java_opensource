package cn.itsource.dao;

import java.util.List;

import cn.itsource.domain.Jobs;
import cn.itsource.util.PageBeanUtil;

public interface IJobsDao {

	/*List<Jobs> list();*/

	void delete(Integer id);

	void add(Jobs jobs);

	Jobs findById(Jobs jobs);

	void update(Jobs jobs);

	Integer findNum();

	List<Jobs> list(PageBeanUtil<Jobs> pageBean);

	Integer indexFindNum();

	List<Jobs> indexList(PageBeanUtil<Jobs> pageBean);

	Integer indexFindNum(String sql);

	List<Jobs> indexList(PageBeanUtil<Jobs> pageBean, String sql);

}
