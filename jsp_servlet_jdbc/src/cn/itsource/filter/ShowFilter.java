package cn.itsource.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowFilter implements Filter {
	FilterConfig config;
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		Object username = request.getSession().getAttribute("username");
		if(username == null)
			response.sendRedirect("/jsp822/");
		fc.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
