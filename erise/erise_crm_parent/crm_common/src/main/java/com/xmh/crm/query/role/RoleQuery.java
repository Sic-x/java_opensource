package com.xmh.crm.query.role;

import com.xmh.basic.query.BaseQuery;

public class RoleQuery extends BaseQuery {
    //当前页
    private Long page=1L;

    //每页显示多少条
    private Long pageSize = 20l;

    //每个高级查询都有keywords
    private String keywords;

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    //-----------------------------------------------------------------

    //获取起始页
// 0 10   10-20 20 30
    public Long getStart(){
        return (this.page-1)*pageSize;
    }
}
