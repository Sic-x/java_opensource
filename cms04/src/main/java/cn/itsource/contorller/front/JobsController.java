package cn.itsource.contorller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itsource.domain.Jobs;
import cn.itsource.service.ICityService;
import cn.itsource.service.IJobsService;
import cn.itsource.util.JobsCondition;
import cn.itsource.util.PageBeanUtil;

@Controller
@RequestMapping("/jobs")
public class JobsController {
	
	@Autowired
	private IJobsService service;
	
	@RequestMapping("/list")
	public String list(Model model,Integer localPage,JobsCondition condition){
		System.out.println("list"+condition.getTitle());
		PageBeanUtil<Jobs> pageBean= service.list(localPage,condition);
		model.addAttribute("pageBean",pageBean);
		model.addAttribute("condition",condition);
		return "join_us_info";
	}
	
}
