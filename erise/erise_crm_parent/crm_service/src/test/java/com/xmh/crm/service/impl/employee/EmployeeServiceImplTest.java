package com.xmh.crm.service.impl.employee;

import com.xmh.crm.domain.employee.Employee;
import com.xmh.crm.mapper.employee.EmployeeMapper;
import com.xmh.crm.query.employee.EmployeeQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceImplTest {

    @Autowired
    EmployeeMapper employeeMapper;
    @Test
    public void test() throws Exception {
        employeeMapper.queryData(new EmployeeQuery()).forEach(e->
                System.out.println(e)
        );
    }


}