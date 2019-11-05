package com.xmh.aisell.domain;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission extends BaseDomain {

    //权限名称
    private String name;
    //权限资源(路径)
    private String url;
    //描述
    private String descs;
    //编码
    private String sn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

}
