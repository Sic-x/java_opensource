package com.xmh.crm.domain.repairAll;

import com.xmh.basic.domain.BaseDomain;
import com.xmh.crm.domain.opt.Opt;
import com.xmh.crm.domain.parts.Parts;
import com.xmh.crm.domain.repairOrder.RepairOrder;
import com.xmh.crm.domain.repairOrderItem.RepairOrderItem;

import java.math.BigDecimal;

public class RepairAll extends BaseDomain {


    private String custormer;

    /**
     * 车牌号
     */
    private String carnum;


    /**
     * 客户地址
     */
    private String address;

    //-----------------------------------------------------------------

    /**
     * 维修人员
     */
    private Opt opt;


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

    //-----------------------------------------------------------------

    /**
     * 维修工单号
     */


    /**
     * 维修配件
     * 接待人员根据车辆情况填写
     */
    private Parts parts;

    public String getCustormer() {
        return custormer;
    }

    public void setCustormer(String custormer) {
        this.custormer = custormer;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Opt getOpt() {
        return opt;
    }

    public void setOpt(Opt opt) {
        this.opt = opt;
    }

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


    public Parts getParts() {
        return parts;
    }

    public void setParts(Parts parts) {
        this.parts = parts;
    }

    public static RepairOrder getRepairOrder(RepairAll repairAll){
            RepairOrder repairOrder = new RepairOrder();
            repairOrder.setCustormer(repairAll.getCustormer());
            repairOrder.setAddress(repairAll.getAddress());
            repairOrder.setCarnum(repairAll.getCarnum());
            repairOrder.setOpt(repairAll.getOpt());
            return  repairOrder;
    }

    public static RepairOrderItem getRepairOrderItem(RepairAll repairAll){
        RepairOrderItem item = new RepairOrderItem();
        item.setAmt1(repairAll.getParts().getPrice());
        item.setOpt(repairAll.getOpt());
        item.setAmt2(repairAll.getAmt2());
        item.setNum(repairAll.getNum());
        item.setParts(repairAll.getParts());
        return  item;
    }



    @Override
    public String toString() {
        return "RepairAll{" +
                "custormer='" + custormer + '\'' +
                ", carnum='" + carnum + '\'' +

                ", address='" + address + '\'' +
                ", opt=" + opt +
                ", amt1=" + amt1 +
                ", amt2=" + amt2 +
                ", num=" + num +

                ", parts=" + parts +
                '}';
    }
}
