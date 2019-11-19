package com.xmh.crm.domain.permission;

import com.xmh.basic.domain.BaseDomain;
import com.xmh.crm.domain.role.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统权限
 */
public class Permission extends BaseDomain {

    /**
     * 权限名称
     */
    private String name;

    /**
     * 资源地址
     */
    private String resource;
//-----------------------------------------------------------------
    /**
     *相关角色
     */
    private List<Role> roleList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", resource='" + resource + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
