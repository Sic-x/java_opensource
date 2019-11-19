package com.xmh.crm.shiro.util;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        MyUsernamePasswordToken mupt = (MyUsernamePasswordToken) token;
        if (mupt.getLoginType().equals(LoginType.NOPASSWD)) {
            //免密登录
            return true;
        }

        /*if (mupt.getLoginType().equals(LoginType.PASSWORD)) {
            //密码登录
            return super.doCredentialsMatch(token, info);
        } else {
            return false;
        }*/
        return super.doCredentialsMatch(token, info);
    }
}
