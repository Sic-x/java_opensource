package ssjtest;

import cn.itsource.dao.IEmployeeDao;
import cn.itsource.domain.Employee;
import cn.itsource.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SSJTest {

    /*@Autowired
    private DataSource dataSource;

    @Test
    public void test() throws Exception {
        //测试数据源对象是否创建成功
        System.out.println(dataSource);
        //测试是否获得连接
        System.out.println(dataSource.getConnection());
    }*/

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private IEmployeeDao dao;

    @Autowired
    private IEmployeeService service;


    @Test
    public void test() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    }


    @Test
    public void testDao() throws Exception {
        dao.findAll();
    }

    @Test
    public void testService() throws Exception {
//        List<Employee> list = service.findAll();
//        System.out.println(list);
        Employee employee = new Employee();
        employee.setName("周");
        service.save(employee);
    }

}
