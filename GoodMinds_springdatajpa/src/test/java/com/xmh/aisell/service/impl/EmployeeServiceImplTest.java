package com.xmh.aisell.service.impl;


import com.xmh.aisell.BaseSpringTest;
import com.xmh.aisell.domain.Employee;
import com.xmh.aisell.service.IEmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class EmployeeServiceImplTest extends BaseSpringTest{

    @Autowired
    private IEmployeeService employeeService;

    /**
     * @throws Exception
     */
    @Test
    public void testFindAll() throws Exception {
        employeeService.findAll().forEach(employee -> System.out.println(employee));
    }

    /**
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        Employee employee = new Employee();
        employee.setUsername("老勇");
        employeeService.save(employee);
    }

}