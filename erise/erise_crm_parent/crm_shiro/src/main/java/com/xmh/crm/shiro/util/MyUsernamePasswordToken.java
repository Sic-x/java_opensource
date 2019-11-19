package com.xmh.crm.shiro.util;


import org.apache.shiro.authc.UsernamePasswordToken;

public class MyUsernamePasswordToken extends UsernamePasswordToken {

    private String loginType;

    public MyUsernamePasswordToken() {
        super();
    }
    /**账号密码登录*/
    public MyUsernamePasswordToken(String username, String password, String loginType, boolean rememberMe,  String host) {
        super(username, password, rememberMe, host);
        this.loginType = loginType;
    }

    /**免密登录*/
    public MyUsernamePasswordToken(String username) {
        super(username, "", false, null);
        this.loginType = LoginType.NOPASSWD;
    }

    public MyUsernamePasswordToken(String username, String password) {
        super(username, password, false, null);
        this.loginType = LoginType.PASSWORD;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}

