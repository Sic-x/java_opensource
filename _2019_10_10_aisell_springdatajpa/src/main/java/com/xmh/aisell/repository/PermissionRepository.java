package com.xmh.aisell.repository;

import com.xmh.aisell.domain.Permission;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


public interface PermissionRepository extends BaseRepository<Permission,Long> {

    /**
     *  根据当前用户获取对应的权限（只拿权限的sn）
     *  JPQL关联法则 1.关连的是前面的对象别名.属性 2.消除笛卡尔积
     *  select o from Employee o join o.department
     */
    @Query("select p.sn from Employee e join e.roles r join r.permissions p where e.id = ?1")
    Set<String> findSnByUser(Long userid);
}
