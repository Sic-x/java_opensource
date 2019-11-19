package com.xmh.crm.shiro.util;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class CrmSessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "X-TOKEN";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public CrmSessionManager() {
        super();
    }

    //覆写 获取sessionid
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //取到jessionid
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        HttpServletRequest request1 = (HttpServletRequest) request;
        //如果请求头中有 X-TOKEN 则其值为sessionId
        if (!StringUtils.isEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        } else {
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }

}