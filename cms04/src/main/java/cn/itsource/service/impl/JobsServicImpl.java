package cn.itsource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.dao.IJobsDao;
import cn.itsource.domain.Jobs;
import cn.itsource.service.IJobsService;
import cn.itsource.util.FreeMarkerUtil;
import cn.itsource.util.JobsCondition;
import cn.itsource.util.PageBeanUtil;

@Service
public class JobsServicImpl implements IJobsService {
	@Autowired
	private IJobsDao dao;
	/*@Override
	public List<Jobs> list() {
		return dao.list();
	}*/
	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}
	@Override
	public void add(Jobs jobs) {
		
		String templatePath = "D:\\itsource\\作业\\WebWorkSpace\\2019_09_11_cms04\\src\\main\\webapp\\templates";
		String templateName = "join_us_details.ftl";
		String htmlurl = FreeMarkerUtil.createHtm(templatePath, templateName, jobs, ".html");
		
		jobs.setHtmlurl(htmlurl);
		dao.add(jobs);
	}
	@Override
	public Jobs findById(Jobs jobs) {
		return dao.findById(jobs);
	}
	@Override
	public void update(Jobs jobs) {
		
		String templatePath = "D:\\itsource\\作业\\WebWorkSpace\\2019_09_11_cms04\\src\\main\\webapp\\templates";
		String templateName = "join_us_details.ftl";
		String htmlurl = FreeMarkerUtil.createHtm(templatePath, templateName, jobs, ".html");
		
		jobs.setHtmlurl(htmlurl);
		
		dao.update(jobs);
		
	}
	@Override
	public PageBeanUtil<Jobs> list(Integer localPage) {
		Integer totalNum = dao.findNum();
		if(localPage==null)
			localPage = 1;
		PageBeanUtil<Jobs> pageBean = new PageBeanUtil<>(localPage, totalNum);
		pageBean.setList(dao.list(pageBean));
		return pageBean;
	}
	@Override
	public PageBeanUtil<Jobs> indexList(Integer localPage) {
		Integer totalNum = dao.indexFindNum();
		if(localPage==null)
			localPage = 1;
		PageBeanUtil<Jobs> pageBean = new PageBeanUtil<>(localPage, totalNum);
		pageBean.setList(dao.indexList(pageBean));
		return pageBean;
	}
	@Override
	public PageBeanUtil<Jobs> list(Integer localPage, JobsCondition condition) {
		String sql = JobsCondition.getCondition(condition.getTitle(), condition.getPositiontype());
		Integer totalNum = dao.indexFindNum(sql);
		if(localPage==null)
			localPage = 1;
		PageBeanUtil<Jobs> pageBean = new PageBeanUtil<>(localPage, totalNum);
		pageBean.setList(dao.indexList(pageBean,sql));
		return pageBean;
	}

}
