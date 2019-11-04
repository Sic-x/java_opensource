package cn.itsource.dao;

import cn.itsource.domain.Employee;
import java.util.List;


public interface IEmployeeDao {

    void save(Employee employee);
    void update(Employee employee);
    void delete(Long id);
    Employee findOne(Long id);
    List<Employee> findAll();
}
