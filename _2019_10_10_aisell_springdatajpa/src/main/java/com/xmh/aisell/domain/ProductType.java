package com.xmh.aisell.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "producttype")
public class ProductType extends BaseDomain{
    //产品名称
    private String name;
    //产品描述
    private String descs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ProductType parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public ProductType getParent() {
        return parent;
    }

    public void setParent(ProductType parent) {
        this.parent = parent;
    }


}
