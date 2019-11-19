package com.xmh.crm.service.role;


import com.xmh.basic.query.BaseQuery;
import com.xmh.basic.service.BaseService;
import com.xmh.crm.domain.role.Role;
import com.xmh.crm.query.role.RoleQuery;
import com.xmh.util.PageList;

import java.io.Serializable;

public interface IRoleService extends BaseService<Role> {
    //保存角色和权限
    public void saveRoleAndPermission(Role role);

    public void delete(Long id);
    public void updatetoto(Role role);
    PageList query(RoleQuery roleQuery);
}
