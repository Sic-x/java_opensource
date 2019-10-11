package cn.itsource.util;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyHttpServletRequest extends HttpServletRequestWrapper{
	HttpServletRequest request;
	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	@Override
	public String getParameter(String name) {
		Map<String, String[]> map = request.getParameterMap();
		return map.get(name)[0];
	}
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = request.getParameterMap();
		Set<String> keySet = map.keySet();
		for (String ks : keySet) {
			String[] values = map.get(ks);
			for (int i = 0; i < values.length; i++) {
				values[i] = values[i].replaceAll("老勇", "傻逼");
			}
		}
		return map;
	}
	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> map = request.getParameterMap();
		return map.get(name);
	}

}
