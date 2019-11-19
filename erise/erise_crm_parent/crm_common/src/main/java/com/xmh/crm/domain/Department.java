package com.xmh.crm.domain;


import com.xmh.basic.domain.BaseDomain;

public class Department extends BaseDomain {

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +

                ", name='" + name + '\'' +
                '}';
    }
}
