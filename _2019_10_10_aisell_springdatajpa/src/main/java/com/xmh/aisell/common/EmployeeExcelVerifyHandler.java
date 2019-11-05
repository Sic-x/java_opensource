package com.xmh.aisell.common;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.xmh.aisell.domain.Employee;
import com.xmh.aisell.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeExcelVerifyHandler implements IExcelVerifyHandler<Employee>{

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public ExcelVerifyHandlerResult verifyHandler(Employee employee) {

        ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult(true);
        Employee username = employeeService.findByUsername(employee.getUsername());
        if(username!=null){
            result.setSuccess(false);
            result.setMsg("用户名重复");
        }
        return result;
    }
}
