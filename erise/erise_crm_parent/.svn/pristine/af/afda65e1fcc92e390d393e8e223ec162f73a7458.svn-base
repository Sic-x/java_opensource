package com.xmh.crm.web.controller.permission;


import com.xmh.crm.domain.permission.Permission;
import com.xmh.crm.query.permission.PermissionQuery;
import com.xmh.crm.service.permission.IPermissionService;
import com.xmh.util.AjaxResult;
import com.xmh.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/permission")
@CrossOrigin
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;
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

    /*@RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public List<Permission> findAll(){
        return permissionService.findAll();
    }*/

    /**
     * 分页方法
     * @param permissionQuery
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin
    public PageList<Permission> query(@RequestBody PermissionQuery permissionQuery) {
        return permissionService.query(permissionQuery);
    }

    //  get departement/12 查询一条
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Permission findOne(@PathVariable("id") Long id){

        return permissionService.findOne(id);
    }

    //新增  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult saveOrUpdate(@RequestBody  Permission permission){
        try {
            if (permission.getId() != null){
                permissionService.update(permission);
            }else{
                permissionService.save(permission);
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
    public AjaxResult update(@RequestBody Permission permission){
        permissionService.update(permission);
        return new AjaxResult();
    }*/

    //删除 {"id":1,"name":"xxxx"}
    @RequestMapping(value = "/batchRemove",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult batchRemove(String ids){
        Arrays.asList(ids.split(",")).forEach(e->
                permissionService.remove(new Long(e))
        );
        return new AjaxResult();
    }
}
