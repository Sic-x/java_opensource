package cn.itsource.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginFilter implements Filter {
	FilterConfig config;
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		req.setCharacterEncoding(config.getInitParameter("encoding"));
		resp.setContentType("text/html;charset="+config.getInitParameter("encoding"));
		fc.doFilter(req, resp);
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;

	}

}
