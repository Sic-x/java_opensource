package cn.itsource.contorller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itsource.domain.Jobs;
import cn.itsource.service.IJobsService;
import cn.itsource.util.PageBeanUtil;

@Controller
public class IndexController {
	
	@Autowired
	private IJobsService service;
	
	@RequestMapping("/system")
	public String systemIndex(){
		return "WEB-INF/system/index";
	}
	
	@RequestMapping("/index")
	public String index(Integer localPage,Model model){
		PageBeanUtil<Jobs> pageBean = service.indexList(localPage);
		model.addAttribute("pageBean",pageBean);
		return "index";
	}
}
