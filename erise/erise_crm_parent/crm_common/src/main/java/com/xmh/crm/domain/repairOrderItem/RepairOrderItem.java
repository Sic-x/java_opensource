package com.xmh.crm.domain.repairOrderItem;

import com.xmh.basic.domain.BaseDomain;
import com.xmh.crm.domain.opt.Opt;
import com.xmh.crm.domain.parts.Parts;
import com.xmh.crm.domain.repairOrder.RepairOrder;

import java.math.BigDecimal;

/**
 * 维修明细
 */
public class RepairOrderItem extends BaseDomain {

    /**
     * 配件价格
     * 维修人员填写
     */
    private BigDecimal amt1;

    /**
     * 工时费
     * 维修员工时费用
     */
    private BigDecimal amt2;

    /**
     * 配件数量
     *
     */
    private Long num;

    /**
     * 总金额
     * 维修的总金额
     */
    private BigDecimal totalamt;

    //-----------------------------------------------------------------

    /**
     * 维修工单号
     */
    private RepairOrder repairOrder;

    /**
     * 维修员
     * 根据维修工单生成
     */
    private Opt opt;

    /**
     * 维修配件
     * 接待人员根据车辆情况填写
     */
    private Parts parts;



    //-----------------------------------------------------------------

    public BigDecimal getAmt1() {
        return amt1;
    }

    public void setAmt1(BigDecimal amt1) {
        this.amt1 = amt1;
    }

    public BigDecimal getAmt2() {
        return amt2;
    }

    public void setAmt2(BigDecimal amt2) {
        this.amt2 = amt2;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public BigDecimal getTotalamt() {
        BigDecimal num = BigDecimal.valueOf(this.num);
        BigDecimal multiply = num.multiply(this.amt1);
        this.totalamt = multiply.add(this.amt2);
        return this.totalamt;
    }

    public void setTotalamt(BigDecimal totalamt) {
        this.totalamt = totalamt;
    }

    //-----------------------------------------------------------------


    public RepairOrder getRepairOrder() {
        return repairOrder;
    }

    public void setRepairOrder(RepairOrder repairOrder) {
        this.repairOrder = repairOrder;
    }

    public Opt getOpt() {
        return opt;
    }

    public void setOpt(Opt opt) {
        this.opt = opt;
    }

    public Parts getParts() {
        return parts;
    }

    public void setParts(Parts parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return "RepairOrderItem{" +
                "amt1=" + amt1 +
                ", amt2=" + amt2 +
                ", num=" + num +
                ", totalamt=" + totalamt +
                ", repairOrder=" + repairOrder +
                ", opt=" + opt +
                ", parts=" + parts +
                '}';
    }
}
