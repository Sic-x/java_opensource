package cn.itsource.service.impl;

import cn.itsource.dao.IEmployeeDao;
import cn.itsource.domain.Employee;
import cn.itsource.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//业务层
@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService{

    @Autowired
    private IEmployeeDao dao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Employee employee) {
        dao.save(employee);
    }

    @Override
    public void update(Employee employee) {
        dao.update(employee);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * 事物的传播机制
     *      Propagation.REQUIRED【默认且大部分】
     *      如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
     *      Propagation.SUPPORTS【常用】
     *      支持当前事务，如果当前没有事务，就以非事务方式执行。
     *      Propagation.MANDATORY
     *      使用当前的事务，如果当前没有事务，就抛出异常。
     *      Propagation.REQUIRES_NEW【日志常用】
     *      新建事务，如果当前存在事务，把当前事务挂起。
     *      Propagation.NOT_SUPPORTED
     *      以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
     *      Propagation.NEVER
     *      以非事务方式执行，如果当前存在事务，则抛出异常。
     *      Propagation.NESTED
     *      如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。
     * */

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Employee findOne(Long id) {
        return dao.findOne(id);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Employee> findAll() {
        return dao.findAll();
    }
}
