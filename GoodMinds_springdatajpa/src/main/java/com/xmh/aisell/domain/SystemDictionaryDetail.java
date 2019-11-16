package com.xmh.aisell.domain;


import javax.persistence.*;

@Entity
@Table(name = "systemdictionarydetail")
public class SystemDictionaryDetail extends BaseDomain{

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "types_id")
    private SystemDictionaryType systemDictionaryType;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SystemDictionaryType getSystemDictionaryType() {
        return systemDictionaryType;
    }

    public void setSystemDictionaryType(SystemDictionaryType systemDictionaryType) {
        this.systemDictionaryType = systemDictionaryType;
    }
}
