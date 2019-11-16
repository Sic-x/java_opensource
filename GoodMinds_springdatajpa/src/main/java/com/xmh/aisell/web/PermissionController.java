package com.xmh.aisell.web;

import com.xmh.aisell.common.JsonResult;
import com.xmh.aisell.common.UIPage;
import com.xmh.aisell.domain.Permission;
import com.xmh.aisell.query.PermissionQuery;
import com.xmh.aisell.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/index")
    public String index(){
        return "permission/index";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Permission> findAll(){
        return permissionService.findAll();
    }

    /**
     * 准备分页方法
     * @param query
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage page(PermissionQuery query){
        //return permissionService.queryPage(query);
        return new UIPage(permissionService.queryPage(query));
    }

    /**
     * @ModelAttribute:只要你通过前台调用我的方法，我就要执行这个方法中的内容
     *
     */
    @ModelAttribute("editPermission")
    public Permission beforeUpdate(Long id, String cmd){
        //我想要的是，只有修改执行相应功能
        if(id!=null && "update".equals(cmd)) {
            //根据id拿到相应的对象
            Permission permission = permissionService.findOne(id);
            //把关连对应的值设置为空,就可以解决n-to-n的问题
            return permission;
        }
        return null;
    }

    /**
     * 前台会传相应的数据(Permission)过来
     *  需要返回：JsonResult{success:true/false,msg:"xx"}
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Permission permission){
        return this.saveOrUpdate(permission);
    }

    //修改方法
    /**
     *我们的这个permission对象中从 beforeUpdate中获取的
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editPermission") Permission permission){
        return this.saveOrUpdate(permission);
    }

    //添加或者修改
    public JsonResult saveOrUpdate(Permission permission){
        try {
            permissionService.save(permission);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }

    /**
     * 前台会传一个id过来
     *  需要返回：{success:true/false,msg:"xx"}
     *     返回这种结构有两种方法:1种是写map, 1种是直接返回对象
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
        try {
            permissionService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            //失败后返回的结果
            return new JsonResult(false,e.getMessage());
        }
    }


}

