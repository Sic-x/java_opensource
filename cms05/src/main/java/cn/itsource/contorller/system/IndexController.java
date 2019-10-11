package cn.itsource.contorller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itsource.domain.Imgs;
import cn.itsource.domain.Jobs;
import cn.itsource.service.IImgsService;
import cn.itsource.service.IJobsService;
import cn.itsource.util.PageBeanUtil;

@Controller
public class IndexController {
	
	@Autowired
	private IJobsService service;
	
	@Autowired
	private IImgsService iService;
	
	@RequestMapping("/system")
	public String systemIndex(){
		return "WEB-INF/system/index";
	}
	
	@RequestMapping("/login")
	public String systemLogin(){
		return "login";
	}
	
	@RequestMapping("/index")
	public String index(Integer localPage,Model model){
		// 轮播图
		List<Imgs> imgs = iService.indexImgs();
		model.addAttribute("imgs", imgs);
		//职位列表
		PageBeanUtil<Jobs> pageBean = service.indexList(localPage);
		model.addAttribute("pageBean",pageBean);
		return "index";
	}
}
