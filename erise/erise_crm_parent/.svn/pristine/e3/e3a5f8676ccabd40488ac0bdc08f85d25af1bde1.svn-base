package com.xmh.crm.service.impl.role;

import com.xmh.basic.query.BaseQuery;
import com.xmh.basic.service.impl.BaseServiceImpl;
import com.xmh.crm.domain.permission.Permission;
import com.xmh.crm.domain.role.Role;
import com.xmh.crm.mapper.role.RoleMapper;
import com.xmh.crm.query.role.RoleQuery;
import com.xmh.crm.service.role.IRoleService;
import com.xmh.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     *  怎么保存角色
     *          t_role
     *          t_role_permission [rid,pid]
     *
     */

    @Override
    public void saveRoleAndPermission(Role role) {
        //保存角色
        roleMapper.save(role);
        Long roleId = role.getId();
        List list = new ArrayList();
        List<Permission> permissionList = role.getPermissionList();
        for (Permission permission : permissionList) {
            Map permissionMap = new HashMap();
            permissionMap.put("roleId",roleId);
            permissionMap.put("permissionId",permission.getId());
            list.add(permissionMap);
        }
        roleMapper.insertRoleAndPermssion(list);


    }

    @Override
    public void delete(Long id) {
        roleMapper.remove(id);
        roleMapper.deleteRolePermssion(id);
    }
    @Override
    public void updatetoto(Role role) {
        roleMapper.update(role);
        roleMapper.deleteRolePermssion(role.getId());
        updateto(role);

    }


    @Override
    public PageList query(RoleQuery roleQuery) {
        //  通过Mapper查询总数
        Long total = roleMapper.querryCount(roleQuery);
        if (total<1){
            return new PageList<>();
        }
        //查询当前页数据
        List rows = roleMapper.queryData(roleQuery);
        return new PageList<>(total,rows);

    }

    void  updateto(Role role){
        Long roleId = role.getId();
        List list = new ArrayList();
        List<Permission> permissionsList = role.getPermissionList();
        for (Permission permission : permissionsList) {
            Map permissionMap = new HashMap();
            permissionMap.put("roleId",roleId);
            permissionMap.put("permissionId",permission.getId());
            list.add(permissionMap);
        }

        roleMapper.insertRoleAndPermssion(list);
    }





}
