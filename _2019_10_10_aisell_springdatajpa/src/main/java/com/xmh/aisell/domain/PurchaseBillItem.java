package com.xmh.aisell.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "purchasebillitem")
public class PurchaseBillItem extends BaseDomain {
    /**
     * 单价 不能为空，至少为0
     * 不一定为产品成本价一样
     * 需要自己添加
     */
    private BigDecimal price;

    /**
     * 数量 不能为空，至少为0
     * 需要自己添加
     */
    private BigDecimal num;

    /**
     * 自动计算 小计 = 价格 * 数量
     */
    private BigDecimal amount;

    /**
     * 产品描述 需要自己填写
     */
    private String descs;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     *  组合关系，不能为空
     */
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "bill_id")
    @JsonIgnore
    private PurchaseBill bill;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PurchaseBill getBill() {
        return bill;
    }

    public void setBill(PurchaseBill bill) {
        this.bill = bill;
    }
}
