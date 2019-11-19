package com.xmh.crm.web.controller.role;


import com.xmh.crm.domain.role.Role;
import com.xmh.crm.query.role.RoleQuery;
import com.xmh.crm.service.role.IRoleService;
import com.xmh.util.AjaxResult;
import com.xmh.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/role")
@CrossOrigin
public class RoleController {

    @Autowired
    private IRoleService roleService;

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

//    @RequestMapping(method = RequestMethod.PATCH)
//    @ResponseBody
//    public List<Role> findAll(){
//        return roleService.findAll();
//    }

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin
    public PageList<Role> query(@RequestBody RoleQuery roleQuery) {
        return roleService.query(roleQuery);
    }

    //  get departement/12 查询一条
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Role findOne(@PathVariable("id") Long id){
        return roleService.findOne(id);
    }

    //新增  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult saveOrUpdate(@RequestBody  Role role){
        try {
            if (role.getId() != null){
                roleService.updatetoto(role);
            }else{
                roleService.saveRoleAndPermission(role);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败!"+e.getMessage());
        }
    }

   /* //修改  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@RequestBody Role Role){
        RoleService.update(Role);
        return new AjaxResult();
    }*/

    //删除 {"id":1,"name":"xxxx"}
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id){
        roleService.delete(id);
        return new AjaxResult();
    }
    @RequestMapping(value = "/batchRemove",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult batchRemove(String ids){
        Arrays.asList(ids.split(",")).forEach(e->
                roleService.delete(new Long(e))
        );
        return new AjaxResult();
    }
}
