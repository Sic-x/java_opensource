package cn.itsource.contorller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itsource.domain.City;
import cn.itsource.domain.Jobs;
import cn.itsource.service.sysytem.ICityService;
import cn.itsource.service.sysytem.IJobsService;

@Controller
@RequestMapping("/system/jobs")
public class SystemJobsController {
	
	@Autowired
	private IJobsService service;
	
	@Autowired
	private ICityService cityService;
	
	@RequestMapping("/list")
	public String list(Model model){
		List<Jobs> jobs = service.list();
		model.addAttribute("jobs",jobs);
		return "WEB-INF/system/jobs";
	}
	
	@RequestMapping("/delete")
	public String delete(Jobs jobs){
		service.delete(jobs.getId());
		return "forward:list";
	}
	
	@RequestMapping("/addPage")
	public String addPage(Model model){
		List<City> city = cityService.list();
		model.addAttribute("city",city);
		return "WEB-INF/system/jobs_add";
	}
	
	@RequestMapping("/add")
	public String add(Model model,Jobs jobs){
		service.add(jobs);
		return "forward:list";
	}
	
	@RequestMapping("/updatePage")
	public String updatePage(Model model,Jobs jobs){
		Jobs newJobs = service.findById(jobs);
		List<City> city = cityService.list();
		model.addAttribute("newJobs",newJobs);
		model.addAttribute("city",city);
		return "WEB-INF/system/jobs_edit";
	}
	
	@RequestMapping("/update")
	public String update(Model model,Jobs jobs){
		System.out.println(jobs);
		service.update(jobs);
		return "forward:list";
	}
	
}
