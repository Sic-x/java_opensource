package com.xmh.aisell.service;

import com.xmh.aisell.domain.Department;

/**
 * 部门业务层接口
 * 继承基础业务层
 *
 */
public interface IDepartmentService extends IBaseService<Department,Long> {
    Department findByName(String name);
}
