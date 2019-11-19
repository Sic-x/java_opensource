package com.xmh.crm.domain.employee;


import com.xmh.basic.domain.BaseDomain;
import com.xmh.crm.domain.Department;

import java.util.Date;

public class Employee  extends BaseDomain {

    private String username;    // 员工账号  文本      not null
    private String realName;    // 真实姓名  文本      not null
    private String password;    // 密码      文本      not null
    private String tel;         // 电话      文本      not null
    private String email;       // 邮箱      文本
    private String address;
    private Department dept;    // 部门      文本
    private Employee tenant;                                  // 租户
    private Date inputTime = new Date();     // 录入时间  日期
    private Integer state;      // 状态      数字    0 正常 ，-1离职
    private Boolean type = false;//是否管理员
    private String open_id;

    private String headimg;

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Employee getTenant() {
        return tenant;
    }

    public void setTenant(Employee tenant) {
        this.tenant = tenant;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", dept=" + dept +
                ", tenant=" + tenant +
                ", inputTime=" + inputTime +
                ", state=" + state +
                ", type=" + type +
                '}';
    }
}