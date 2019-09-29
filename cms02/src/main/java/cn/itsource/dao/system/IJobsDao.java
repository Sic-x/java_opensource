package cn.itsource.dao.system;

import java.util.List;

import cn.itsource.domain.Jobs;

public interface IJobsDao {

	List<Jobs> list();

	void delete(Integer id);

	void add(Jobs jobs);

	Jobs findById(Jobs jobs);

	void update(Jobs jobs);

}
