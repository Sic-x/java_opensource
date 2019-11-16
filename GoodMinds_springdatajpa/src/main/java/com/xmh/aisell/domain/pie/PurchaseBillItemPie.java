package com.xmh.aisell.domain.pie;

import com.xmh.aisell.domain.BaseDomain;

import java.math.BigDecimal;

public class PurchaseBillItemPie extends BaseDomain {
    private String name;
    private BigDecimal amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
