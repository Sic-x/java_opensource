package com.xmh.aisell.repository;

import com.xmh.aisell.domain.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 员工持久层
 */
public interface EmployeeRepository extends BaseRepository<Employee,Long> {

    /**
     * 模糊查询员工
     * @param username 员工名
     * @return 对应员工集合
     */
    //jpql
    @Query("select o from Employee o where o.username like ?1")
    List<Employee> Query01(String username);

    /**
     * 查询所有员工
     * @param username 员工名
     * @return 对应员工集合
     */
    //原生sql
    @Query(nativeQuery = true,value = "SELECT * FROM emploee")
    List<Employee> Query02(String username);


    /**
     * @param username
     * @return
     */
    //高级查询，根据方法名（用户名查询）
    Employee findByUsername(String username);

    /**
     * @param username
     * @return
     */
    //模糊查询，用户名
    List<Employee> findByUsernameLike(String username);

    /**
     * @param username
     * @param email
     * @return
     */
    //用户名与邮件模糊查询 username like ? and email like ?
    List<Employee> findByUsernameLikeAndEmailLike(String username,String email);

    @Query("select o from Employee o where o.department.name = ?1")
    List<Employee> findBuyers(String deptName);
}
