package cn.itsource.contorller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.itsource.domain.Admin;
import cn.itsource.service.IAdminService;

@Controller
@RequestMapping("/system/admin")
public class SystemAdminController {
	
	@Autowired
	private IAdminService service;
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req){
		HttpSession session = req.getSession();
		session.invalidate();
		return "forward:/login.jsp";
	}
	
	@RequestMapping("/checkName")
	@ResponseBody
	public String checkName(String username){
		if(username!=null && !username.trim().equals("")){
			Admin admin  = service.checkName(username);
			if(admin==null){
				System.out.println("ok");
				return "ok";
			}else{
				System.out.println("no");
				return "no";
			}
		}else{
			System.out.println("null");
			return "null";
		}
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest req,Admin admin,String inputCode,Model model){
		HttpSession session = req.getSession();
		//获取session中绑定的验证码
		Object randomCode = session.getAttribute("RANDOMCODE_IN_SESSION");
		session.removeAttribute("RANDOMCODE_IN_SESSION");//移除session找那个绑定的验证码（防止重复提交）

		if(randomCode == null) {
			model.addAttribute("name", admin.getUsername());
			model.addAttribute("loginError", "验证码过期");
			return "forward:/login.jsp";
		}else if(!inputCode.equalsIgnoreCase(randomCode.toString())) {//忽略大小写
			model.addAttribute("loginError", "验证码错误");
			return "forward:/login.jsp";
		}else {
			//3.调用service
			if(service.login(admin)){
				req.getSession().setAttribute("username", admin.getUsername());
				return "forward:/system";
			}else{
				req.setAttribute("loginError", "密码错误");
				return "forward:/login.jsp";
			}
		}		
	}
	
	@RequestMapping("/regsiter")
	public String regsiter(Admin admin){
		service.regsiter(admin);
		return "forward:/login.jsp";
	}
}
