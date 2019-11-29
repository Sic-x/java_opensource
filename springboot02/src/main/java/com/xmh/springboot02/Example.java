package com.xmh.springboot02;


import com.xmh.springboot02.user_properties.UserConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



//@RestController
@Controller
@Slf4j //lombok注解
public class Example {

	/*private static Logger logger = LoggerFactory.getLogger(Example.class);*/

	@Autowired
	private UserConfig userConfig;

	@RequestMapping("/")
	@ResponseBody
	String home() {

		//日志的级别
		//trace -> debug -> info -> warn -> error
		/*logger.error("错误");
		logger.warn("警告");
		logger.info("信息");
		logger.debug("调试");
		logger.trace("追踪");*/

		log.error("错误");
		log.warn("警告");
		log.info("信息");
		log.debug("调试");
		log.trace("追踪");

		log.info(userConfig.toString());

		return "Hello World!!!";
	}

	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("msg","00");
		return "hello";
	}



}