package com.xmh.aisell.web.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AiSellPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter{

    //请求头的所有数据都封装到请求对象中
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() == null) {
            this.saveRequestAndRedirectToLogin(request, response);
        } else {
            //确定是否是Ajax请求，如果是， 就返回json数据，如果不是，执行原来的代码
            //1.把请求和响应对象转到Http的方式(功能更强大一些)
            HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse resp = (HttpServletResponse)response;
            //2.根据请求对象拿到请求头中 X-Requested-With:XMLHttpRequest
            String xRequestedWith = req.getHeader("X-Requested-With");
            if("XMLHttpRequest".equals(xRequestedWith)){
                //3.Ajax请求的处理
                //设置响应头为Json
                resp.setContentType("application/json;charset=UTF-8");
                //通过响应流把json数据返回回去
                resp.getWriter().print("{\"success\":false,\"msg\":\"没有权限\"}");
            }else {
                //获取到没有权限的地址
                String unauthorizedUrl = this.getUnauthorizedUrl();
                if (StringUtils.hasText(unauthorizedUrl)) {
                    //如果有地址就跳转
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    //如果没有地址就报401错误
                    WebUtils.toHttp(response).sendError(401);
                }
            }
        }
        return false;
    }
}
