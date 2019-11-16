package com.xmh.aisell.common;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 每次各实体类都会查询生成页面对象
 * 抽取页面对象
 * @param <T> 调用此类的实体类型
 */
public class UIPage<T> {

    private Long total; //总条数
    private List<T> rows; //每页数据

    /**
     * 前后端的数据key值不一样需要修改后重新封装
     * @param page
     */
    public UIPage(Page page) {
        total = page.getTotalElements();
        rows = page.getContent();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
