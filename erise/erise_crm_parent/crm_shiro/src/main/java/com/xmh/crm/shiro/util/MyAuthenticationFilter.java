package com.xmh.crm.shiro.util;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest request1 = (HttpServletRequest) request;
        //获取提交的方式
        String method = request1.getMethod();
        if("OPTIONS".equals(method)){
            return true;
        }

        return super.isAccessAllowed(request, response, mappedValue);
    }

    //是否免密方法
   /* @Override
    protected AuthenticationToken createToken(String username, String password, ServletRequest request, ServletResponse response) {
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        String loginType = LoginType.PASSWORD;

        if(request.getParameter("loginType")!=null && !"".equals(request.getParameter("loginType").trim())){
            loginType = request.getParameter("loginType");
        }

        return new MyUsernamePasswordToken(username, password,loginType,rememberMe,host);
    }*/


}
