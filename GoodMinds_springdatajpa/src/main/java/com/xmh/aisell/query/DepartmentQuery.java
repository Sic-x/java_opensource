package com.xmh.aisell.query;

import com.github.wenhao.jpa.Specifications;
import com.xmh.aisell.domain.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 部门查询
 * 所有部门的查询条件与方法都写在部门查询类中
 * 但是分页与排序所有的查询类都可以使用一套相同的方法
 * 所有需要将分页和排序抽取写在BaseQuery以提高代码的复用性
 * */
public class DepartmentQuery extends BaseQuery{

    //Employee实体类的字段属性(ID已被抽取)
    //部门名
    private String name;

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
                .like(StringUtils.isNoneBlank(name), "name", "%" + name + "%")
                .build();
        return specification;
    }

    //提供getter setter方法

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }


    //toString
    @Override
    public String toString() {
        return "EmployeeQuery{" +
                "name='" + name + '}';
    }
}
