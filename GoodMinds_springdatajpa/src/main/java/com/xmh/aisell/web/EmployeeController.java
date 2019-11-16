package com.xmh.aisell.web;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.xmh.aisell.common.JsonResult;
import com.xmh.aisell.common.UIPage;
import com.xmh.aisell.domain.Employee;
import com.xmh.aisell.query.EmployeeQuery;
import com.xmh.aisell.service.IEmployeeService;
import com.xmh.aisell.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工控制层
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    /**
     * 自动注入IBaseService实现对象
     */
    @Autowired
    private IEmployeeService service;





    @RequestMapping("/index")
    public String employeeIndex(){
        return "employee/index";
    }




    /**
     * 请求url匹配
     * 返回json字符串
     * @return 查询所有员工数据
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public List FindAll(){
        return service.findAll();
    }

    @RequestMapping("/findBuyers")
    @ResponseBody
    public List FindBuyers(){
        return service.findBuyers();
    }

    /**
     * 准备分页方法
     * @param query 前台传入的query对象
     * //@param page  前台传过来的page字符串与query对象CurrentPage不同
     * @return      返回UIPage对象,由queryPage查询封装
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Employee> page(EmployeeQuery query){
        //String page
        //query.setCurrentPage(new Integer(page));
        //List content = service.queryPage(query).getContent();
        return new UIPage(service.queryPage(query));
    }

    /**
     * @param id 前台传入的id字符串
     *        调用service.delete
     * @return 返回相应的json字符串到前台
     * 返回结构 1.写map
     *         2.直接返回对象
     */

    /**
     * 前台会传相应的数据(Employee)过来
     *  需要返回：JsonResult{success:true/false,msg:"xx"}
     *  后面要做用户权限，需要把save和update分开，有些用户只有添加没有修改权限
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Employee employee){
        return saveOrUpdate(employee);
    }


    /**
     * 更新前方法，前台发送请求/employee/update?cmd=update
     * 只匹配更新请求通过发送过来的id从数据库获取相对应的Employee持久化对象
     * （这里有个n to n的问题 事务期间关联持久化对象的创建造成脏数据更新报错）
     * @param id
     * @param cmd
     * @return
     */
    @ModelAttribute("editEmployee")
    public Employee beforeUpdate(Long id,String cmd){
        if("update".equals(cmd)){
            Employee employee = service.findOne(id);
            //解决n-to-n问题，将与employee关联的持久化对象设置为空，让spring重新创建
            employee.setDepartment(null);
            return employee;
        }
        return null;
    }

    /**
     *
     * @param employee 传入参数employee现在是从beforeUpdate方法拿的
     *        前台依旧会传过来与数据库中持久化对象employee不同的修改数据
     *        spring会根据不同的修改，‘.’前get ‘.’后set
     * @return JsonResult
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editEmployee")Employee employee){
        return saveOrUpdate(employee);
    }

    /**
     * @param employee
     *  执行service.save(employee);方法
     *
     * @return 成功返回 new JsonResult();
     *         失败返回 new JsonResult(false,e.getMessage());
     */
    public JsonResult saveOrUpdate(Employee employee){
        try {
            JsonResult jsonResult = new JsonResult();
            //判断是添加还是修改
            if(employee.getId() == null){
                jsonResult.setAdd(true);
            }
            service.save(employee);
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }


    /**
     * 删除方法
     * @param id 根据 Employee id 删除员工
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
        try {
            service.delete(id);
            return new JsonResult();
        } catch (Exception e) {
           // return "failure";
            return new JsonResult(false,e.getMessage());
        }
       // return "success";
    }

    /**
     * 导出方法
     * 将一个流从服务器端导出到客户端
     *
     */
    @RequestMapping("/export")
    public String export(EmployeeQuery query,ModelMap map, HttpServletRequest req){
        //根据查询条件拿到所有数据
        List<Employee> list = service.queryAll(query);
        //获取图片真实路径
        String realPath = req.getServletContext().getRealPath("");
        ExportParams params = new ExportParams("员工表", "工作表1", ExcelType.XSSF);
        //冻结列
        params.setFreezeCol(2);
        //拼接设置图片路径
        list.forEach(e->{
            e.setHeadImage(realPath+e.getHeadImage());
        });

        map.put(NormalExcelConstants.DATA_LIST,list);
        map.put(NormalExcelConstants.CLASS,Employee.class);
        map.put(NormalExcelConstants.PARAMS,params);
        map.put(NormalExcelConstants.FILE_NAME,"员工");

        return NormalExcelConstants.EASYPOI_EXCEL_VIEW;
    }

    @RequestMapping("/headImg")
    public String headImage(){
        return "headImage/index";
    }

    @RequestMapping("/headImageUpload")
    public String headImageUpload(){
        return null;
    }

}
