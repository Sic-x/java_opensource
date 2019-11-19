package com.xmh.util;

public class WxConstants {
    //第三方认证的appid
    public  final static String APPID = "wxd853562a0548a7d0";

    //用户授权后微信的回调域名 localhost/callback
    public final static String CALLBACK="http://bugtracker.itsource.cn/callback";

    //固定作用域
    public final static String SCOPE = "snsapi_login";

    //应用密码
    public final static String APPSECRET = "4a5d5615f93f24bdba2ba8534642dbb6";

    //微信上获取code的地址 拉起二维码
    public final static String CODEURL = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=CALLBACK&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    //微信上获取at的地址
    public final static String ACCESSTOKEURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    //微信上获取用户信息的地址
    public final static String USERINFOURL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";


}
