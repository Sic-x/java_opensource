package com.xmh.crm.domain.repairOrder;

import com.xmh.basic.domain.BaseDomain;
import com.xmh.crm.domain.opt.Opt;

import java.util.Date;

/**
 * 维修单
 */
public class RepairOrder extends BaseDomain {

    /**
     * 客户名称
     */
    private String custormer;

    /**
     * 车牌号
     */
    private String carnum;

    /**
     * 维修单创建时间
     */
    private Date createtime=new Date();

    /**
     * 维修单状态
     */
    private boolean status=true;

    /**
     * 客户地址
     */
    private String address;

    //-----------------------------------------------------------------

    /**
     * 维修人员
     */
    private Opt opt;

    //-----------------------------------------------------------------

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //-----------------------------------------------------------------


    public Opt getOpt() {
        return opt;
    }

    public void setOpt(Opt opt) {
        this.opt = opt;
    }

    @Override
    public String toString() {
        return "RepairOrder{" +
                "custormer='" + custormer + '\'' +
                ", carnum='" + carnum + '\'' +
                ", createtime=" + createtime +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", opt=" + opt +
                '}';
    }
}
