package cn.itsource.controller;

import cn.itsource.domain.Employee;
import cn.itsource.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//控制层
@Controller
//请求匹配
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService service;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("/findAll")
    @ResponseBody   //返回json不通过视图解析器所必须
    public List<Employee> findAll(){
        return service.findAll();
    }
}
