package com.xmh.crm.web.controller.user;




import com.xmh.crm.domain.user.User;
import com.xmh.crm.service.user.IUserService;
import com.xmh.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     *  restful 风格 -- http的风格（get/post）
     *
     *   put -- 新增
     *   post -- 修改
     *   delete -- 删除
     *   get --查询
     *   patch --查询
     *
     *  (1)查询所有
     */

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public List<User> findAll(){
        return userService.findAll();
    }

    //  get user/12 查询一条
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public User findOne(@PathVariable("id") Long id){
        return userService.findOne(id);
    }

    //新增  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult saveOrUpdate(@RequestBody  User user){
        try {
            if (user.getId() != null){
                userService.update(user);
            }else{
                userService.save(user);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败!"+e.getMessage());
        }
    }

/*    //修改  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@RequestBody User user){
        userService.update(user);
        return new AjaxResult();
    }*/

    //删除 {"id":1,"name":"xxxx"}
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id){
        userService.remove(id);
        return new AjaxResult();
    }
}
