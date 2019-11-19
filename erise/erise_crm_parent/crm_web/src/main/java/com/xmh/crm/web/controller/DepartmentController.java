package com.xmh.crm.web.controller;


import com.xmh.crm.domain.Department;
import com.xmh.crm.query.DepartmentQuery;
import com.xmh.crm.service.department.IDepartmentService;
import com.xmh.util.AjaxResult;
import com.xmh.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

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

    @RequestMapping(value = "/findAll",method = RequestMethod.PATCH)
    @ResponseBody
    public List<Department> findAll(){
        return departmentService.findAll();
    }

    /**
     * 分页方法
     * @param departmentQuery
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin
    public PageList<Department> query(@RequestBody DepartmentQuery departmentQuery) {
        return departmentService.query(departmentQuery);
    }


    //  get departement/12 查询一条
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Department findOne(@PathVariable("id") Long id){
        return departmentService.findOne(id);
    }

    //新增  {"id":1,"name":"xxxx"}
    //修改  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult saveOrUpdate(@RequestBody  Department department){
        try {
            if (department.getId() != null){
                departmentService.update(department);
            }else{
                departmentService.save(department);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败!"+e.getMessage());
        }
    }


    /*@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@RequestBody Department department){
        departmentService.update(department);
        return new AjaxResult();
    }*/

    //删除 {"id":1,"name":"xxxx"}
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id){
        departmentService.remove(id);
        return new AjaxResult();
    }

    //删除 {"id":1,"name":"xxxx"}
    @RequestMapping(value = "/batchRemove",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult batchRemove(String ids){
        Arrays.asList(ids.split(",")).forEach(e->
                departmentService.remove(new Long(e))
        );
        return new AjaxResult();
    }
}
