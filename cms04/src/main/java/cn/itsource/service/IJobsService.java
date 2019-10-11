package cn.itsource.service;

import cn.itsource.domain.Jobs;
import cn.itsource.util.JobsCondition;
import cn.itsource.util.PageBeanUtil;

public interface IJobsService {

	/*List<Jobs> list();*/

	void delete(Integer id);

	void add(Jobs jobs);

	Jobs findById(Jobs jobs);

	void update(Jobs jobs);

	PageBeanUtil<Jobs> list(Integer localPage);

	PageBeanUtil<Jobs> indexList(Integer localPage);

	PageBeanUtil<Jobs> list(Integer localPage, JobsCondition condition);

}
