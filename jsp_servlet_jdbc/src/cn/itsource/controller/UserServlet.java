package cn.itsource.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itsource.dao.impl.UserDaoImpl;
import cn.itsource.model.User;
import cn.itsource.util.FileUploadUtils;

@WebServlet("/userServlet/*")
@MultipartConfig
public class UserServlet extends HttpServlet{
	HttpSession session;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User(req.getParameter("username"),req.getParameter("password"),
				req.getParameter("nickname"));
		//增
		if(req.getRequestURI().equals("/jsp822/userServlet/add")){
			new UserDaoImpl().add(user);
			session = req.getSession();
			List<User> list = new UserDaoImpl().query();
			session.setAttribute("user",user);
			session.setAttribute("userList",list);
			resp.sendRedirect("/jsp822/System/user.jsp");	
		}
		//改
		if(req.getRequestURI().equals("/jsp822/userServlet/update")){
			new UserDaoImpl().update(user);
			session = req.getSession();
			List<User> list = new UserDaoImpl().query();
			session.setAttribute("user",user);
			session.setAttribute("userList",list);
			resp.sendRedirect("/jsp822/System/user.jsp");	
		}
		//删
		if(req.getRequestURI().equals("/jsp822/userServlet/delete")){
			new UserDaoImpl().delete(user);	
			session = req.getSession();
			List<User> list = new UserDaoImpl().query();
			session.setAttribute("user",user);
			session.setAttribute("userList",list);
			resp.sendRedirect("/jsp822/System/user.jsp");	
		}
		//查询单个
		if(req.getRequestURI().equals("/jsp822/userServlet/queryone")){
			User result = new UserDaoImpl().query(req.getParameter("name"));
			session = req.getSession();
			session.setAttribute("result", result);
			resp.sendRedirect("/jsp822/System/user.jsp");	
		}
		//查询多个
		if(req.getRequestURI().equals("/jsp822/userServlet/querylike")){
			List<User> result = new UserDaoImpl().querylike(req.getParameter("name"));
			session = req.getSession();
			session.setAttribute("result", result);
			resp.sendRedirect("/jsp822/System/user.jsp");	
		}
		//登录
		if(req.getRequestURI().equals("/jsp822/userServlet/login")){
			//登录成功
			session = req.getSession();
			session.setAttribute("user",user);
			session.setAttribute("username",user.getUsername());
			if (session.getAttribute("RANDOMCODE_IN_SESSION").toString().equalsIgnoreCase(req.getParameter("code"))) {
				if(new UserDaoImpl().login(user)){
					List<User> list = new UserDaoImpl().query();
					session.setAttribute("userList",list);
					resp.sendRedirect("/jsp822/System/user.jsp");	
				}
				else{
					session.setAttribute("loginFailed","账号或密码错误");
					resp.sendRedirect("/jsp822/");	
				}
			}
			else{
				session.setAttribute("loginFailed","验证码错误");
				resp.sendRedirect("/jsp822/");	
			}
		}
		//注册
		if(req.getRequestURI().equals("/jsp822/userServlet/regist")){
			session = req.getSession();
			session.setAttribute("flag","注册失败");
			if(req.getParameter("password").equals(req.getParameter("password2"))){
				if(new UserDaoImpl().regist(user)){
					session.setAttribute("flag","注册成功");
					FileUploadUtils.fileUpload(req, "filename");
				}
			}
			resp.sendRedirect("/jsp822/signup.jsp");
		}
	}
}
