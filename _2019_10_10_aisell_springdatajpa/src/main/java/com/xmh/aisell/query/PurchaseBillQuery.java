package com.xmh.aisell.query;

import com.github.wenhao.jpa.Specifications;
import com.xmh.aisell.domain.PurchaseBill;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PurchaseBillQuery extends BaseQuery {

    //开始时间
    private Date beginDate;
    //结束时间
    private Date endDate;
    //状态
    private Integer status;

    //查询的规则应该在查询对象中来创建
    @Override
    public Specification createSpec(){
        //如果结束时间存在，就要加一天
        if(endDate!=null){
            //生成一个新的值，会加一天
            endDate = DateUtils.addDays(endDate,1);
        }

        Specification<PurchaseBill> specification = Specifications.<PurchaseBill>and()
                .ge(beginDate!=null,"vdate",beginDate)
                //注意:结束时间加了一天(只需要小于它即可)
                .lt(endDate!=null,"vdate",endDate)
                .eq(status!=null,"status",status)
                .build();
        return specification;
    }

    public Date getBeginDate() {
        return beginDate;
    }


    /**
     * 前台日期格式转换不能忘
     * @param beginDate
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    /**
     * 前台日期格式转换不能忘
     * @param endDate
     */
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
}
