package com.xmh.aisell.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product extends BaseDomain{

    private String name;
    private String color;
    private String pic;
    private String smallPic;
    /**
     * 参考成本价
     */
    private BigDecimal costPrice;

    /**
     * 参考销售价
     */
    private BigDecimal salePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private SystemDictionaryDetail unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private SystemDictionaryDetail brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "types_id")
    private ProductType productType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public SystemDictionaryDetail getUnit() {
        return unit;
    }

    public void setUnit(SystemDictionaryDetail unit) {
        this.unit = unit;
    }

    public SystemDictionaryDetail getBrand() {
        return brand;
    }

    public void setBrand(SystemDictionaryDetail brand) {
        this.brand = brand;
    }


}
