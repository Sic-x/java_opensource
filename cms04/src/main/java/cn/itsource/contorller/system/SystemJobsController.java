package cn.itsource.contorller.system;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itsource.domain.City;
import cn.itsource.domain.Jobs;
import cn.itsource.service.ICityService;
import cn.itsource.service.IJobsService;
import cn.itsource.util.PageBeanUtil;

@Controller
@RequestMapping("/system/jobs")
public class SystemJobsController {
	
	@Autowired
	private IJobsService service;
	
	@Autowired
	private ICityService cityService;
	
	@RequestMapping("/list")
	public String list(Model model,Integer localPage,Integer fromAdd,Integer totalPage){
		/*List<Jobs> jobs = service.list();
		model.addAttribute("jobs",jobs);
		return "WEB-INF/system/jobs";*/
		if(fromAdd!=null){
			localPage = totalPage;
		}
		PageBeanUtil<Jobs> pageBean = service.list(localPage);
		model.addAttribute("pageBean",pageBean);
		return "WEB-INF/system/jobs";
	}
	
	@RequestMapping("/delete")
	public String delete(Jobs jobs,String htmlurl,HttpServletRequest req){
		service.delete(jobs.getId());
		
		String path = req.getServletContext().getRealPath("/templates");
		
		File file = new File(path, htmlurl);
		if(file.exists())
			file.delete();
		return "forward:list";
	}
	
	@RequestMapping("/addPage")
	public String addPage(Model model,Integer totalPage){
		List<City> city = cityService.list();
		model.addAttribute("city",city);
		model.addAttribute("totalPage",totalPage);
		return "WEB-INF/system/jobs_add";
	}
	
	@RequestMapping("/add")
	public String add(Model model,Jobs jobs){
		service.add(jobs);
		return "forward:list";
	}
	
	@RequestMapping("/updatePage")
	public String updatePage(Model model,Jobs jobs,Integer localPage){
		Jobs newJobs = service.findById(jobs);
		List<City> city = cityService.list();
		model.addAttribute("newJobs",newJobs);
		model.addAttribute("city",city);
		model.addAttribute("localPage",localPage);
		return "WEB-INF/system/jobs_edit";
	}
	
	@RequestMapping("/update")
	public String update(Model model,Jobs jobs,HttpServletRequest req){
		
		String path = req.getServletContext().getRealPath("/templates");
		
		File file = new File(path, jobs.getHtmlurl());
		if(file.exists())
			file.delete();
		
		service.update(jobs);
		return "forward:list";
	}
	
}
