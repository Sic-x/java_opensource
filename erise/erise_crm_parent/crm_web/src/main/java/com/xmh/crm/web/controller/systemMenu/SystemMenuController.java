package com.xmh.crm.web.controller.systemMenu;


import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.xmh.crm.domain.employee.Employee;
import com.xmh.crm.domain.systemMenu.SystemMenu;
import com.xmh.crm.service.employee.IEmployeeService;
import com.xmh.crm.service.systemMenu.ISystemMenuService;
import com.xmh.util.AjaxResult;
import com.xmh.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/systemMenu")
@CrossOrigin
public class SystemMenuController {

    @Autowired
    private ISystemMenuService systemMenuService;
    @Autowired
    private IEmployeeService employeeService;

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
    public List<SystemMenu> findAll(){
            return systemMenuService.findAll();


    }

    //  get departement/12 查询一条
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public SystemMenu findOne(@PathVariable("id") Long id){
        return systemMenuService.findOne(id);
    }

    //新增  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult saveOrUpdate(@RequestBody  SystemMenu systemMenu){
        try {
            if (systemMenu.getId() != null){
                systemMenuService.update(systemMenu);
            }else{
                systemMenuService.save(systemMenu);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败!"+e.getMessage());
        }
    }

    /*//修改  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@RequestBody SystemMenu systemMenu){
        systemMenuService.update(systemMenu);
        return new AjaxResult();
    }*/

    //删除 {"id":1,"name":"xxxx"}
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id){
        systemMenuService.remove(id);
        return new AjaxResult();
    }

    @RequestMapping(value = "/permission",method = RequestMethod.POST)
    @ResponseBody
    public String findAllByPermission(){
        System.out.println("进来了");
        Employee user = (Employee) UserContext.getUser();
        Employee employee= employeeService.findOne(user.getId());
        System.out.println(employee);
        if(employee.getDept()==null) {
            System.out.println("企业账户");
            return systemMenuService.findAllByPermission("t").toString();
        }else
            return systemMenuService.findAllByPermission("e").toString();
    }
}
