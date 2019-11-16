package com.xmh.aisell.service.impl;

import com.xmh.aisell.domain.Department;
import com.xmh.aisell.repository.DepartmentRepository;
import com.xmh.aisell.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 部门业务层
 * 继承基础业务层
 * @Service 主动注入生成实现类
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department,Long> implements IDepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }
}
