package cn.itsource.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg) throws Exception {
		String uri = req.getRequestURI();// '/cms/system' uri和url都不包括后面的请求参数
		//StringBuffer url = req.getRequestURL(); // 'http://localhost/cms/system'
		if(uri.indexOf("login")!=-1){
			return true;
		}
		
		Object obj = req.getSession().getAttribute("username");
		if(obj!=null){
			return true;
		}
		resp.sendRedirect("/cms/login.jsp");
		return false;
	}

}
