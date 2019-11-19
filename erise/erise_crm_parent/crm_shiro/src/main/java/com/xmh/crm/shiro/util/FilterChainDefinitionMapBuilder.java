package com.xmh.crm.shiro.util;



import com.xmh.crm.service.permission.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;

import java.util.Map;

//得到Map
public class FilterChainDefinitionMapBuilder {
    @Autowired
    private IPermissionService permissionService;
    public Map<String,String> createFilterChainDefinitionMap(){
        Map<String, String> map = new LinkedHashMap();
        //静态资源放行
        map.put("*.js","anon");
        map.put("*.css","anon");
        map.put("/css/**","anon");
        map.put("/js/**","anon");
        map.put("/easyui/**","anon");
        map.put("/images/**","anon");
        map.put("/resources/**","anon");
        map.put("/binder.jsp","anon");
        map.put("/binder","anon");
        map.put("/s/login.jsp","anon");
        map.put("/upload/*","anon");
        map.put("/employee/checkName","anon");
        map.put("/file/*","anon");
        map.put("/register","anon");

        map.put("/login","anon");
        map.put("/callback","anon");
        //这个值之后从数据库中查询到【用户-角色-权限-资源】

        //map.put("/department", "rest[department]");
        map.put("/**","myAuth");
        return  map;
    }
}
