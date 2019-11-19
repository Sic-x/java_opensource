package com.xmh.crm.shiro.realm;


import com.xmh.crm.domain.employee.Employee;
import com.xmh.crm.service.employee.IEmployeeService;
import com.xmh.crm.shiro.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

//realm
public class AuthenRealm extends AuthorizingRealm {

    @Autowired
    IEmployeeService employeeService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取令牌
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //获取数据库用户
        Employee employee = employeeService.getByUsername(username);
        if(employee==null){
            //用户不存在
            throw new UnknownAccountException(username);
        }
        //存在 取出密码 和 传入的进行比对
        Object principal = employee;
        Object hashedCredentials = employee.getPassword();
        ByteSource credentialsSalt = ByteSource.Util.bytes(MD5Util.SALT);
        String realmName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,hashedCredentials,credentialsSalt,realmName);
        return info;
    }
}
