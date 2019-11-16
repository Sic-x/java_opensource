package com.xmh.aisell.query;

import com.github.wenhao.jpa.Specifications;
import com.xmh.aisell.domain.PurchaseBillItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PurchaseBillItemQuery extends BaseQuery {

    //开始时间
    private Date beginDate;
    //结束时间
    private Date endDate;
    //状态
    private Integer status;
    //分组的值
    private Integer groupBy = 1;

    //查询的规则应该在查询对象中来创建
    @Override
    public Specification createSpec(){
        //如果结束时间存在，就要加一天
        if(endDate!=null){
            //生成一个新的值，会加一天
            endDate = DateUtils.addDays(endDate,1);
        }
        Specification<PurchaseBillItem> specification = Specifications.<PurchaseBillItem>and()
                .ge(beginDate!=null,"bill.vdate",beginDate)
                //注意:结束时间加了一天(只需要小于它即可)
                .lt(endDate!=null,"bill.vdate",endDate)
                .eq(status!=null,"bill.status",status)
                .build();
        return specification;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(Integer groupBy) {
        this.groupBy = groupBy;
    }

}
