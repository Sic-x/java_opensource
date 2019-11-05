package com.xmh.aisell.web;

import com.xmh.aisell.common.JsonResult;
import com.xmh.aisell.common.UserContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    /**
     *
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String index(){
        return "login";
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(String username, String password){
        System.out.println(username);
        System.out.println(password);
        //拿到当前用户(有可能是游客)
        Subject subject = SecurityUtils.getSubject();
        //直接登录
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return new JsonResult(false,"用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return new JsonResult(false,"密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new JsonResult(false,"未知错误");
        }
        UserContext.putUser();
        return new JsonResult();
    }

    @RequestMapping("/logout")
    public String login(){
        //完成注销功能
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
