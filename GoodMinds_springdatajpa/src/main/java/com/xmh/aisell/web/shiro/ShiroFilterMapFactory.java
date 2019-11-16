package com.xmh.aisell.web.shiro;

import com.xmh.aisell.domain.Permission;
import com.xmh.aisell.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于返回下面的这些值(这里的值是有顺序的：LinkedHashMap)
 *   <value>
         /login = anon
         /s/permission.jsp = perms[user:index]
         /** = authc
    </value>
    这里修改后要重新启动tomcat
 */
public class ShiroFilterMapFactory {

    @Autowired
    private IPermissionService service;

    public Map<String,String> createMap(){
        Map<String,String> map = new LinkedHashMap<>();
        //anon:需要放行的路径
        map.put("/login","anon");
        map.put("/login999","anon");
        //perms:权限拦截
        //所有静态资源放行
        map.put("*.js","anon");
        map.put("*.css","anon");
        map.put("/css/**","anon");
        map.put("/js/**","anon");
        map.put("/easyui/**","anon");
        map.put("/images/**","anon");

        List<Permission> perms = service.findAll();
        perms.forEach(p -> {
            map.put(p.getUrl(),"aisellPerms["+p.getSn()+"]");
        });
        //authc:拦截
        map.put("/**","authc");
        return map;
    }
}
