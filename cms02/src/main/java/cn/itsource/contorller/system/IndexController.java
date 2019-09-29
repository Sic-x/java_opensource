package cn.itsource.contorller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/system")
	public String systemIndex(){
		return "WEB-INF/system/index";
	}
}
