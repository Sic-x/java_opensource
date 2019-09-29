package cn.itsource.service.sysytem;

import java.util.List;

import cn.itsource.domain.Jobs;

public interface IJobsService {

	List<Jobs> list();

	void delete(Integer id);

	void add(Jobs jobs);

	Jobs findById(Jobs jobs);

	void update(Jobs jobs);

}
