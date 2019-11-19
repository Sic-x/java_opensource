package com.xmh.crm.domain.systemMenu;

import com.xmh.basic.domain.BaseDomain;

import java.util.List;

/**
 * 系统菜单
 */
public class SystemMenu extends BaseDomain {

    /**
     * 菜单编号
     */
    private String sn;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 地址
     */
    private String url;

    /**
     * 简介
     */
    private String intro;

    //-----------------------------------------------------------------

    /**
     * 上级菜单
     */
    private SystemMenu parent;

    //-----------------------------------------------------------------

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    //-----------------------------------------------------------------


    public SystemMenu getParent() {
        return parent;
    }

    public void setParent(SystemMenu parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "SystemMenu{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", intro='" + intro + '\'' +
                ", parent=" + parent +
                '}';
    }
}
