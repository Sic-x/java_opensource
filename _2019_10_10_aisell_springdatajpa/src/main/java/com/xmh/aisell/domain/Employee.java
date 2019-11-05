package com.xmh.aisell.domain;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工实体类
 */
@Entity
@Table(name = "employee")
public class Employee extends BaseDomain{

    @Excel(name = "员工姓名")
    @NotNull(message = "用户名不能为空")
    private String username;

    private String password;

    @Excel(name = "年龄")
    @Max(value = 80,message = "最大值不能超过80")
    @Min(value = 16,message = "最小值不能超过16")
    private Integer age;

    @Excel(name = "邮箱",width = 20)
    @Pattern(regexp = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?",message = "不是正确的邮件格式")
    private String email;

    //头像
    //@Column(updatable = false)
    @Excel(name = "头像",type = 2,width = 15,height = 30)
    private String headImage;

    //部门
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @ExcelEntity
    //@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    private Department department;

    /**
     * 员工与角色是多对多的关系
     * JoinTable:配置的是中间表的信息
     */
    @ManyToMany
    @JoinTable(name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


}
