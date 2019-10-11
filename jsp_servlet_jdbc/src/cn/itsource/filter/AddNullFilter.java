package cn.itsource.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AddNullFilter implements Filter {
	FilterConfig config;
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(username == null || username.trim().equals("")){
			req.setAttribute("addError","用户名不能为空");
			req.getRequestDispatcher("/System/user_add.jsp").forward(req, resp);
			return;
		}
		else if(password == null || password.trim().equals("")){
			req.setAttribute("addError","密码不能为空");
			req.getRequestDispatcher("/System/user_add.jsp").forward(req, resp);
			return;
		}
		fc.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
