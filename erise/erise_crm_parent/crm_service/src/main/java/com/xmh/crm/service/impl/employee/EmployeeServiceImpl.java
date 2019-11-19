package com.xmh.crm.service.impl.employee;


import com.xmh.basic.service.impl.BaseServiceImpl;
import com.xmh.crm.domain.employee.Employee;
import com.xmh.crm.mapper.employee.EmployeeMapper;
import com.xmh.crm.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     *  保存兩個表
     *          t_tenant
     *          t_employee
     *  参数Employee
     *
     */
    public void addTenantEmployee(Employee employee){
        /*//保存租户
        Employee tenant = employee.getTenant();
        tenant.setRegisterTime(new Date());
        tenant.setState(0);
        tenantMapper.save(tenant);*/

        //保存员工
        //employee.setTenant(tenant);

        employee.setInputTime(new Date());
        employeeMapper.save(employee);

    }

    @Override
    public Employee getByUsername(String username) {
        return employeeMapper.getByUsername(username);
    }


}
