package com.xmh.crm.domain.systemlog;

import com.xmh.basic.domain.BaseDomain;

import java.util.Date;

/**
 * 系统日志
 */
public class Systemlog extends BaseDomain {

    /**
     * 操作用户
     */
    private String opUser;

    /**
     * 操作时间
     */
    private Date opTime;

    /**
     * 登录IP
     */
    private String opIp;

    /**
     * 使用功能
     * 方法名称
     */
    private String function;

    /**
     * 操作参数信息
     * 每个操作都会向后台发出请求，如果这个有请求刚好有参数，那么会记录下用于分析
     */
    private String params;

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getOpIp() {
        return opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
