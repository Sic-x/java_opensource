package cn.itsource.dao.impl;

import cn.itsource.dao.IEmployeeDao;
import cn.itsource.domain.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//持久层
@Repository
public class EmployeeDaoImpl implements IEmployeeDao{

    /**
     * 在每次请求实体管理器都只会对应一个对象
     * 使用此注解一个线程只有一次请求
     * Spring将entityManager注入进来,如果没有创建新的对象放在上下文(如线程,请求)中供使用,
     * 如果存在于上下文就直接拿来使用
     * */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public void update(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    public void delete(Long id) {
        Employee employee = findOne(id);
        if(employee!=null)
            entityManager.remove(employee);
    }

    @Override
    public Employee findOne(Long id) {
        return entityManager.find(Employee.class,id);
    }

    @Override
    public List<Employee> findAll() {
        return entityManager.createQuery("from Employee").getResultList();
    }
}
