package com.xmh.crm.query.employee;


import com.xmh.basic.query.BaseQuery;
import com.xmh.crm.domain.employee.Employee;
import com.xmh.util.UserContext;

public class EmployeeQuery extends BaseQuery {

    public Long getTenantID() {
        Employee user = (Employee) UserContext.getUser();
        return user.getId();
    }
}
