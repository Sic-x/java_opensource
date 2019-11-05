package com.xmh.aisell.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xmh.aisell.domain.BaseDomain;
import com.xmh.aisell.domain.PurchaseBillItem;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.Date;

public class PurchaseBillItemVO extends BaseDomain {
    private Long id;
    private String supplier;
    private String buyer;
    private String product;
    private String productType;
    private Date vdate;
    private BigDecimal num;
    private BigDecimal price;
    private BigDecimal amount;
    private Integer status;
    private String groupField;

    public PurchaseBillItemVO(){
    }

    public PurchaseBillItemVO(PurchaseBillItem purchaseBillItem,Integer groupBy){
        this.setId(purchaseBillItem.getId());
        this.setSupplier(purchaseBillItem.getBill().getSupplier().getName());
        this.setBuyer(purchaseBillItem.getBill().getBuyer().getUsername());
        this.setProduct(purchaseBillItem.getProduct().getName());
        this.setProductType(purchaseBillItem.getProduct().getProductType().getName());
        this.setVdate(purchaseBillItem.getBill().getVdate());
        this.setNum(purchaseBillItem.getNum());
        this.setPrice(purchaseBillItem.getPrice());
        this.setAmount(purchaseBillItem.getAmount());
        this.setStatus(purchaseBillItem.getBill().getStatus());
        switch (groupBy) {
            case 1:
                this.groupField = this.supplier;
                break;
            case 2:
                this.groupField = this.buyer;
                break;
            case 3:
                this.groupField = DateFormatUtils.format(this.vdate, "MM") + "月";
                break;
        }
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }

    @Override
    public String toString() {
        return "PurchaseBillItemVO{" +
                "id=" + id +
                ", supplier='" + supplier + '\'' +
                ", buyer='" + buyer + '\'' +
                ", product='" + product + '\'' +
                ", productType='" + productType + '\'' +
                ", vdate='" + vdate + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", amount=" + amount +
                ", status=" + status +
                ", groupField='" + groupField + '\'' +
                '}';
    }
}
