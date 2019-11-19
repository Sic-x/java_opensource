package com.xmh.crm.domain.setted;

import com.xmh.basic.domain.BaseDomain;
import com.xmh.crm.domain.pay.Pay;
import com.xmh.crm.domain.repairOrder.RepairOrder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 结算单
 */
public class Setted extends BaseDomain {
    /**
     * 客户姓名
     */
    private String custormer;









    /**
     * 结算时间
     */
    private Date settedtime;
    /**
     * 应付金额
     */
    private BigDecimal re_amount;
    /**
     * 实付金额
     */
    private BigDecimal pay_amount;

    /**
     * 配件数量
     */
    private String num;
    /**
     * 配件名称
     */
    private String partsname;
    /**
     * 配件价格
     */
    private BigDecimal optPrivce;

    /**
     * 还车状态
     */
    private boolean CarStatus = false;


    //-----------------------------------------------------------------
    /**
     * 维修单
     */
    private RepairOrder repairOrder;
    /**
     * 支付方式
     */
    private Pay pay;
    //-----------------------------------------------------------------

    public boolean isCarStatus() {
        return CarStatus;
    }

    public void setCarStatus(boolean carStatus) {
        CarStatus = carStatus;
    }

    public String getCustormer() {
        return custormer;
    }
    public void setCustormer(String custormer) {
        this.custormer = custormer;
    }
    public Date getSettedtime() {
        return settedtime;
    }
    public void setSettedtime(Date settedtime) {
        this.settedtime = settedtime;
    }
    public BigDecimal getRe_amount() {
        return re_amount;
    }
    public void setRe_amount(BigDecimal re_amount) {
        this.re_amount = re_amount;
    }
    public BigDecimal getPay_amount() {
        return pay_amount;
    }
    public void setPay_amount(BigDecimal pay_amount) {
        this.pay_amount = pay_amount;
    }
    //-----------------------------------------------------------------
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getPartsname() {
        return partsname;
    }
    public void setPartsname(String partsname) {
        this.partsname = partsname;
    }
    public RepairOrder getRepairOrder() {
        return repairOrder;
    }
    public void setRepairOrder(RepairOrder repairOrder) {
        this.repairOrder = repairOrder;
    }
    public Pay getPay() {
        return pay;
    }
    public void setPay(Pay pay) {
        this.pay = pay;
    }

    public BigDecimal getOptPrivce() {
        return optPrivce;
    }

    public void setOptPrivce(BigDecimal optPrivce) {
        this.optPrivce = optPrivce;
    }

    @Override
    public String toString() {
        return "Setted{" +
                "custormer='" + custormer + '\'' +
                ", settedtime=" + settedtime +
                ", re_amount=" + re_amount +
                ", pay_amount=" + pay_amount +
                ", num='" + num + '\'' +
                ", partsname='" + partsname + '\'' +
                ", optPrivce=" + optPrivce +
                ", repairOrder=" + repairOrder +
                ", pay=" + pay +
                '}';
    }
}
