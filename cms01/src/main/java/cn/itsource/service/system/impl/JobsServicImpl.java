package cn.itsource.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.dao.system.IJobsDao;
import cn.itsource.domain.Jobs;
import cn.itsource.service.sysytem.IJobsService;

@Service
public class JobsServicImpl implements IJobsService {
	@Autowired
	private IJobsDao dao;
	@Override
	public List<Jobs> list() {
		return dao.list();
	}
	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

}
