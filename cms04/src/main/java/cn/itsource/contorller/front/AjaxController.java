package cn.itsource.contorller.front;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itsource.domain.Jobs;

@Controller
public class AjaxController {
	
	@RequestMapping("/ajaxGet")
	@ResponseBody
	public Jobs get(String name){
		return new Jobs();
	}
	
	@RequestMapping("/ajaxPost")
	@ResponseBody
	public Integer post(String username){
		return new Random().nextInt();
	}
}
