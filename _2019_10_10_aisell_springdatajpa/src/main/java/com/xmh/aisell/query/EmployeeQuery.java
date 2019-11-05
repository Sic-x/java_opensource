package com.xmh.aisell.query;

import com.github.wenhao.jpa.Specifications;
import com.xmh.aisell.domain.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 员工查询
 * 所有员工的查询条件与方法都写在员工查询类中
 * 但是分页与排序所有的查询类都可以使用一套相同的方法
 * 所有需要将分页和排序抽取写在BaseQuery以提高代码的复用性
 * */
public class EmployeeQuery extends BaseQuery{

    //Employee实体类的字段属性(ID已被抽取)
    //用户名
    private String username;
    //邮件
    private String email;
    //年龄
    private Integer age;
    //部门id
    private Long departmentId;

    /**
     * 用相应的查询类编写查询对象的规则
     * */
    @Override
    public Specification createSpec() {
        /**
         * jpa-spec 简化 JpaSpecificationExecutor
         *
         * */
        Specification<Employee> specification = Specifications.<Employee>and()
                //StringUtils 是commons-lang3工具包提供的工具
                //确认传来的值是否为空或空字符串,如果为空的话则不执行后面的语句,不为空则执行
                .like(StringUtils.isNoneBlank(username), "username", "%" + username + "%")
                .like(StringUtils.isNoneBlank(email), "email", "%" + email + "%")
                .gt(age != null, "age", age)
                .eq(departmentId!=null,"department.id",departmentId)
                .build();
        return specification;
    }

    //提供getter setter方法

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    //toString
    @Override
    public String toString() {
        return "EmployeeQuery{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
