package com.xmh.aisell.service.impl;

import com.xmh.aisell.common.MD5Util;
import com.xmh.aisell.domain.Employee;
import com.xmh.aisell.repository.EmployeeRepository;
import com.xmh.aisell.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 员工业务层
 * 继承基础业务层
 * @Service 主动注入生成实现类
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee,Long> implements IEmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void save(Employee employee) {
        //只对添加的员工进行加密
        if(employee.getId()!=null){
            employee.setPassword(MD5Util.createMD5Str(employee.getPassword()));
        }
        //继承父类的save方法
        super.save(employee);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public List<Employee> findBuyers() {
        return employeeRepository.findBuyers("采购部");
    }
}
