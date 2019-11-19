package com.xmh.crm.web.controller.login;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hazelcast.util.JsonUtil;
import com.xmh.crm.domain.employee.Employee;
import com.xmh.crm.domain.wxuser.WxUser;
import com.xmh.crm.service.employee.IEmployeeService;
import com.xmh.crm.service.wxuser.IWxUserService;
import com.xmh.crm.shiro.util.MyUsernamePasswordToken;
import com.xmh.util.AjaxResult;
import com.xmh.util.HttpClientUtils;
import com.xmh.util.UserContext;
import com.xmh.util.WxConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    private IWxUserService wxUserService;

    @Autowired
    private IEmployeeService employeeService;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult login(@RequestBody Employee employee){
          //拿到主体subject
        Subject subject = SecurityUtils.getSubject();
        //判断subject是否认证过
        if(!subject.isAuthenticated()){
            //如果没有认证 就去登录认证
            //如果认证过，返回到前台
            MyUsernamePasswordToken token = new MyUsernamePasswordToken(employee.getUsername(), employee.getPassword());
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                return new AjaxResult("用户不存在");
            } catch (IncorrectCredentialsException e){
                e.printStackTrace();
                return new AjaxResult("密码错误");
            } catch (AuthenticationException e){
                e.printStackTrace();
                return  new AjaxResult("其他认证异常");
            }
        }

        //把employee信息传到前台，前台放入session(前台session)
        Employee employee1 = (Employee)subject.getPrincipal();

        WxUser wxUser = wxUserService.findWxUserByOpenid(employee.getOpen_id());
        if (wxUser!=null){
            wxUser.setEmpid(employee1.getId());
            wxUserService.update(wxUser);
        }


        UserContext.setUser(employee1);
        AjaxResult ajaxResult = new AjaxResult();
        Map mp = new HashMap<>();
        mp.put("user",employee1);
        //jsessionid -->token
        mp.put("token",subject.getSession().getId());
        ajaxResult.setResultObj(mp);
        //返回对象
        return ajaxResult;
    }

    /**
     * 第三方登录
     * @param model
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult login(Model model){

        String url = WxConstants.CODEURL.replaceAll("APPID", WxConstants.APPID).
                replaceAll("CALLBACK", WxConstants.CALLBACK).
                replaceAll("SCOPE", WxConstants.SCOPE);
        model.addAttribute("wxLoginUrl",url);
        Map<String, String> map = new HashMap<>();
        map.put("url",url);
        AjaxResult result = new AjaxResult();
        result.setResultObj(map);
        return result;
}


    //http://bugtracker.itsource.cn/callback?code=021at63m0Azdir1YVo1m0xTW2m0at63Z&state=STATE
    @RequestMapping(value = "/callback",method = RequestMethod.GET)
    public String callBack(String code,String state) throws Exception {

        //获取到code
        //发送请求获取AK (access token)
        String akUrl = WxConstants.ACCESSTOKEURL.replaceAll("APPID", WxConstants.APPID).
                replaceAll("SECRET", WxConstants.APPSECRET).replaceAll("CODE", code);

        //发送请求 获取AK值
        String responseStr = HttpClientUtils.httpGet(akUrl, null);

        //在发送请求 获取用户信息 json字符串转换成对象
        JSONObject jsonObject = (JSONObject) JSON.parse(responseStr);
        String access_token = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        //发送获取用户信息的请求地址
        String userInforUrl = WxConstants.USERINFOURL.replaceAll("ACCESS_TOKEN", access_token).replaceAll("OPENID", openid);
        String userInfoStr = HttpClientUtils.httpGet(userInforUrl, null);


        //根据openid进行查询用户
        JSONObject userInfoObject = (JSONObject) JSON.parse(userInfoStr);
        String open_id = userInfoObject.getString("openid");
        //判断一下 判断微信用户是否绑定过 如果没有绑定，跳转到绑定页面(用户名和密码)
        WxUser wxUser = wxUserService.findWxUserByOpenid(open_id);
        Subject subject = SecurityUtils.getSubject();
        if(wxUser == null) {
            String nickname = userInfoObject.getString("nickname");
            String headimgurl = userInfoObject.getString("headimgurl");
            boolean sex = userInfoObject.getBoolean("sex");
            String union_id = userInfoObject.getString("unionid");
            wxUser = new WxUser();
            //保存到数据t_wxUser表
            wxUser.setOpenid(open_id);
            wxUser.setNickname(nickname);
            wxUser.setHeadimgurl(headimgurl);
            wxUser.setSex(sex);
            wxUser.setUnionid(union_id);
            wxUserService.save(wxUser);

            //跳转绑定页面 绑定一个用户
            // redirect:http://localhost:8080/bind.vue
            Map<String, String> map = new HashMap<>();
            return "redirect:http://localhost:8080/#/bind?openid="+open_id;


        }else{
            //有这个人 是否绑定过 ，如果没有绑定，进行绑定，如果绑定过，就免密登录
            if(wxUser.getEmpid() != null){
                Employee employee = employeeService.findOne(wxUser.getEmpid());
                MyUsernamePasswordToken token = new MyUsernamePasswordToken(employee.getUsername());
                subject.login(token);
            }

            //把employee信息传到前台，前台放入session(前台session)
            Employee employee1 = (Employee)subject.getPrincipal();
            UserContext.setUser(employee1);
            Map mp = new HashMap<>();
            String token = JSONObject.toJSONString(subject.getSession().getId());
            //返回对象
            System.out.println(employee1);
            return "redirect:http://localhost:8080/echars?username="+employee1.getUsername()+"&id="+employee1.getId()+"&token="+token;
        }
    }

    //username password openid
    @RequestMapping(value = "/binder",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult binder(@RequestBody Map<String,String> map){
        //用户名和密码和openid
        String username=   map.get("username");
        String password = map.get("password");
        String openid = map.get("openid");
        WxUser wxUser = wxUserService.findWxUserByOpenid(openid);
        //完成绑定公共 t_wxuser empid ==设置用户id
        if(wxUser != null){
            //根据username查询用户id
            Employee employee = employeeService.getByUsername(username);
            wxUser.setEmployee(employee);
            wxUserService.update(wxUser);

            wxUser = wxUserService.findWxUserByOpenid(openid);
            //免密登录 -- 认证和授权
            if(wxUser.getEmpid() != null){
                MyUsernamePasswordToken token = new MyUsernamePasswordToken(username);
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);


                return new AjaxResult();
            }

            return new AjaxResult();

        }

        return  new AjaxResult("绑定失败");

    }




}