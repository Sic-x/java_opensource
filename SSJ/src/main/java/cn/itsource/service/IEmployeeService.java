package cn.itsource.service;

import cn.itsource.domain.Employee;

import java.util.List;

public interface IEmployeeService {

    void save(Employee employee);
    void update(Employee employee);
    void delete(Long id);
    Employee findOne(Long id);
    List<Employee> findAll();
}
