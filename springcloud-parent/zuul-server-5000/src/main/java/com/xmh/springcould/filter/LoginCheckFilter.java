package com.xmh.springcould.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginCheckFilter extends ZuulFilter {

    //执行顺序
    public static final int LOGIN_CHECK_ORDER = 1;

    @Override
    public String filterType() {
        //类型:前置
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //执行顺序
        return LOGIN_CHECK_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        //返回值决定了是否执行run方法
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //做业务逻辑
        //请求上下文工具类
        RequestContext currentContext = RequestContext.getCurrentContext();

        //请求对象
        HttpServletRequest request = currentContext.getRequest();

        //相应对象
        HttpServletResponse response = currentContext.getResponse();
        response.setContentType("text/json;charset=utf-8");

        //获取请求头中的token
        String token = request.getHeader("token");

        if(!StringUtils.hasLength(token)){

            try {
                //没登录 ，返回错误信息
                response.setStatus(HttpStatus.SC_UNAUTHORIZED);
                PrintWriter writer = response.getWriter();
                writer.print("没有登录");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //不要继续执行了：不放行
            currentContext.setSendZuulResponse(false);
        }

        return null;
    }
}
