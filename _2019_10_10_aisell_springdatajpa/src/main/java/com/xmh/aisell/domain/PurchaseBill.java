package com.xmh.aisell.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchasebill")
public class PurchaseBill extends BaseDomain {

    /**
     * 交易时间，交易的填写
     */
    private Date vdate;
    /**
     * 不能为空，至少为0
     */
    private BigDecimal totalAmount;
    /**
     *  不能为空，至少为0
     */
    private BigDecimal totalNum;

    /**
     * 录入时间，不需要添加和修改
     */
    private Date inputTime = new Date();
    /**
     * 审核的时候自动生成
     */
    private Date auditorTime;
    /**
     * 添加的时候为       0 待审
     * 领导审核的时候     1 已审
     *                  -1 作废
     */
    private Integer status = 0;


    //必须选择单独供应商，多个供应商需要拆分订单
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    /**
     * 当前审核用户，可以为空及待审
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditor_id")
    private Employee auditor;

    /**
     * 当前录入用户
     */
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "inputUser_id")
    private Employee inputUser;

    /**
     * 当前采购用户
     */
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "buyer_id")
    private Employee buyer;

    @OneToMany(mappedBy = "bill",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<PurchaseBillItem> items = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getAuditorTime() {
        return auditorTime;
    }

    public void setAuditorTime(Date auditorTime) {
        this.auditorTime = auditorTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Employee getAuditor() {
        return auditor;
    }

    public void setAuditor(Employee auditor) {
        this.auditor = auditor;
    }

    public Employee getInputUser() {
        return inputUser;
    }

    public void setInputUser(Employee inputUser) {
        this.inputUser = inputUser;
    }

    public Employee getBuyer() {
        return buyer;
    }

    public void setBuyer(Employee buyer) {
        this.buyer = buyer;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }


    public List<PurchaseBillItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseBillItem> items) {
        this.items = items;
    }


}

