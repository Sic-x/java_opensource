package com.xmh.crm.web.controller.register;


import com.xmh.crm.domain.employee.Employee;
import com.xmh.crm.service.employee.IEmployeeService;
import com.xmh.crm.shiro.util.MD5Util;
import com.xmh.util.AjaxResult;
import com.xmh.util.UserContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping( method = RequestMethod.POST)
    @ResponseBody
    public List<Employee> register(@RequestBody Employee employee){
        employee.setPassword(MD5Util.encrypt(employee.getPassword()));
        employeeService.addTenantEmployee(employee);
        Map map = new HashMap();
        List<Employee> list = new ArrayList<>();
        list.add(employee);
        return list;
    }
}
