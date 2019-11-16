package com.xmh.aisell.repository;

import com.xmh.aisell.domain.Department;
/**
 * 部门持久层
 */
public interface DepartmentRepository extends BaseRepository<Department,Long> {
    //根据名称拿到部门
    Department findByName(String name);
}
