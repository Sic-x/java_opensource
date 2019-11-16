package com.xmh.aisell.service;

import com.xmh.aisell.domain.Employee;

import java.util.List;

/**
 * 员工业务层接口
 * 继承基础业务层
 *
 */
public interface IEmployeeService extends IBaseService<Employee,Long> {
    /**
     * 根据用户名获取员工对象
     * @param username
     * @return
     */
    Employee findByUsername(String username);

    List<Employee> findBuyers();
}
