package com.xmh.crm.domain.role;

import com.xmh.basic.domain.BaseDomain;
import com.xmh.crm.domain.permission.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统角色
 */
public class Role extends BaseDomain {

    /**
     * 角色编号
     */
    private String sn;

    /**
     * 角色名称
     */
    private String name;


//-----------------------------------------------------------------
    /**
     * 相关权限
     */
    private List<Permission> permissionList = new ArrayList<>();

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", permissionList=" + permissionList +
                '}';
    }
}
