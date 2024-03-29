package com.xmh.crm.service.employee;


import com.xmh.basic.service.BaseService;
import com.xmh.crm.domain.employee.Employee;

import java.util.List;

public interface IEmployeeService extends BaseService<Employee> {

     void addTenantEmployee(Employee employee);

     Employee getByUsername(String username);


}
