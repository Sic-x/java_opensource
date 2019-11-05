package com.xmh.aisell.web;

import com.xmh.aisell.common.JsonResult;
import com.xmh.aisell.common.UIPage;
import com.xmh.aisell.domain.Department;
import com.xmh.aisell.query.DepartmentQuery;
import com.xmh.aisell.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 部门控制层
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    /**
     * 自动注入IBaseService实现对象
     */
    @Autowired
    private IDepartmentService service;


    /**
     * 前台请求url匹配
     * @return 通过视图解析器跳转index.jsp
     */
    @RequestMapping("/index")
    public String index(){
        return "department/index";
    }

    /**
     * 请求url匹配
     * 返回json字符串
     * @return 查询所有部门数据
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public List FindAll(){
        return service.findAll();
    }

    /**
     * 准备分页方法
     * @param query 前台传入的query对象
     * //@param page  前台传过来的page字符串与query对象CurrentPage不同
     * @return      返回UIPage对象,由queryPage查询封装
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Department> page(DepartmentQuery query){
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


}
