package com.xmh.aisell.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu extends BaseDomain{
    private String name;
    private String url;
    private String icon;
    @Transient
    private String state = "open";


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Menu parent;
    @Transient
    private List<Menu> children = new ArrayList<>();

 /*   //parent_id
    //配置一对多
    @OneToMany
    @JoinColumn(name = "parent_id")
    private List<Menu> children = new ArrayList<>();
*/

    public String getName() {
        return name;
    }

    public String getText() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
