package com.xmh.aisell.domain;

import javax.persistence.*;

@Entity
@Table(name = "systemdictionarytype")
public class SystemDictionaryType extends BaseDomain{

    public static final String BRAND = "productBrand";
    public static final String UNIT = "productUnit";

    @Column(updatable = false)
    private String sn;
    private String name;


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
}
