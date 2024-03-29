package com.xmh.crm.web.controller.employee;



import com.xmh.crm.domain.employee.Employee;
import com.xmh.crm.query.employee.EmployeeQuery;
import com.xmh.crm.service.employee.IEmployeeService;
import com.xmh.crm.shiro.util.MD5Util;
import com.xmh.util.AjaxResult;
import com.xmh.util.PageList;
import com.xmh.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {

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

   /* @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin
    public List<Employee> findAll(){
        System.out.println("patch:----------查询所有数据-------------");
        return employeeService.findAll();
    }
*/
    /**
     * 分页方法
     * @param employeeQuery
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin
    public PageList<Employee> query(@RequestBody EmployeeQuery employeeQuery) {
        PageList<Employee> list = employeeService.query(employeeQuery);
        System.out.println(list);
        return list;
    }

    //  get departement/12 查询一条
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Employee findOne(@PathVariable("id") Long id){
        Employee employee = employeeService.findOne(id);
        return  employee;
    }

    @RequestMapping(value = "/check",method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult check(@RequestBody Employee e){
        Employee employee = employeeService.findOne(e.getId());
        if(MD5Util.encrypt(e.getPassword()).equals(employee.getPassword())){
            return new AjaxResult();
        }else {
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("旧密码输入错误");
            return  ajaxResult;
        }
    }


    @RequestMapping(value = "/checkName",method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult checkName(@RequestBody String name){
        List<Employee> serviceAll = employeeService.findAll();
        AjaxResult ajaxResult = new AjaxResult();
        for(Employee employee:serviceAll){
            if(employee.getUsername().equals(name)){
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("账号名已经注册");
                return ajaxResult;
            }else {
                ajaxResult = new AjaxResult();
            }
        }
        return ajaxResult;
    }

    //新增  {"id":1,"name":"xxxx"}  保存普通员工
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult save(@RequestBody Employee employee){
        if(employee.getPassword()==null||"".equals(employee.getPassword()))
            employee.setPassword(MD5Util.encrypt("1"));
        else
            employee.setPassword(MD5Util.encrypt(employee.getPassword()));
        Employee user = (Employee) UserContext.getUser();
        employee.setTenant(findOne(user.getId()));
        employeeService.save(employee);
        return new AjaxResult();
    }

    //保存租户
    @RequestMapping(value = "/saveTenantEmployee",method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult saveTenantEmployee(@RequestBody  Employee employee){
        try {
            employee.setPassword(MD5Util.encrypt(employee.getPassword()));
            employeeService.addTenantEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("保存失败:"+e.getMessage());
        }
        return new AjaxResult();
    }

    //修改  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@RequestBody  Employee employee){
        Employee one = employeeService.findOne(employee.getId());
        if(employee.getPassword()==null){
            employee.setPassword(employeeService.findOne(employee.getId()).getPassword());
        }else {
            one.setPassword(MD5Util.encrypt(employee.getPassword()));
        }
        if(employee.getHeadimg()!=null){
            String path ="D:\\itsource\\作业\\IEDAWorkSpace\\_2019_11_08_crm_parent\\crm_web\\src\\main\\webapp";
            File file = new File(path+one.getHeadimg());
            file.delete();
            one.setHeadimg(employee.getHeadimg());
        }else {

            Employee one1 = employeeService.findOne(employee.getId());

            one.setHeadimg(one1.getHeadimg());
        }
        employeeService.update(one);
        return new AjaxResult();
    }

    //删除 {"id":1,"name":"xxxx"}
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        employeeService.remove(id);

        return new AjaxResult();
    }


    public AjaxResult saveOrUpdate(@RequestBody  Employee employee){
        if(employee.getId()!=null){
            employeeService.update(employee);
        }else{
            employeeService.save(employee);
        }
        return new AjaxResult();
    }






}
