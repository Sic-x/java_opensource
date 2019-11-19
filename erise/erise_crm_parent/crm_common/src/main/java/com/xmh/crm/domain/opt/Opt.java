package com.xmh.crm.domain.opt;

import com.xmh.basic.domain.BaseDomain;

import java.util.Date;

/**
 * 维修人员
 */
public class Opt extends BaseDomain {

    /**
     * 维修员名称
     */
    private String optname;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 维修员雇佣日期
     */
    private Date hiredate = new Date();


    public String getOptname() {
        return optname;
    }

    public void setOptname(String optname) {
        this.optname = optname;
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

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }
}
