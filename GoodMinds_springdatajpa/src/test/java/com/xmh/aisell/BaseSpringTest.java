package com.xmh.aisell;

import com.xmh.aisell.common.MD5Util;
import com.xmh.aisell.domain.Employee;
import com.xmh.aisell.service.IEmployeeService;
import com.xmh.aisell.service.IMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BaseSpringTest {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IMenuService menuService;
    @Test
    public void test() throws Exception {

        //1.拿到所有数据
        List<Employee> employeeList = employeeService.findAll();
        //2.循环它，修改密码
        employeeList.forEach(e->{
            //拿到用户名
            String username = e.getUsername();
            System.out.println(username);
            System.out.println(MD5Util.createMD5Str(username));
            e.setPassword(MD5Util.createMD5Str(username));
            employeeService.save(e);
        });
    }


    @Test
    public void test1() throws Exception {
        menuService.findByUser();
    }

}
