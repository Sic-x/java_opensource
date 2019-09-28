package cn.itsource.contorller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itsource.domain.Address;
import cn.itsource.domain.Jobs;
import cn.itsource.service.sysytem.IAddressService;
import cn.itsource.service.sysytem.IJobsService;

@Controller
@RequestMapping("/system/jobs")
public class SystemJobsController {
	
	@Autowired
	private IJobsService service;
	@Autowired
	private IAddressService addressService;
	
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
		List<Address> address = addressService.list();
		model.addAttribute("address",address);
		return "WEB-INF/system/jobs_add";
	}
}
