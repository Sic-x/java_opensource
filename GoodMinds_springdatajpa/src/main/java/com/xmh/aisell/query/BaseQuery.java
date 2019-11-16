package com.xmh.aisell.query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * 抽取父类的作用
 *   1.提供一些公共的属性的方法,提高代码的复用性
 *   2.对子类形成相应的规范,规定方法,字段使子类编写不混乱
 *   3.为了以后代码的扩展性,只需要在父类修改,不需要逐个子类去修改
 *
 * */
public abstract class BaseQuery {

    //字段属性
    //分页属性
    //当前页数,前台数据传入从1开始,后台查询需要修改
    //使用int防止空指针异常
    private int currentPage=1;
    //每页显示条数
    //使用int防止空指针异常
    private int pageSize=10;
    //排序属性
    /**
     * 排序的类型
     * true 降序排列(DESC)
     * false 升序排列(ASC)
     * 使用boolean防止空指针异常
     * */
    private boolean orderType;
    /**
     * 排序的字段
     * 需添加判断,如果orderName为null,则不进行排序
     * */
    private String orderName;


    /**
     * 提供一个抽象JPA执行规范方法,供子类覆写
     * @return 返回规则对象
     * baseRepository.findAll(spec)
     */

    public abstract Specification createSpec();

    /**
     * 创建排序对象
     * @return orderName有值返回排序对象
     * orderName没有值返回null
     */

    public Sort createSort(){
        if(StringUtils.isNotBlank(orderName)){
            //orderName有值才做排序
            Sort sort = new Sort(orderType?Sort.Direction.DESC:Sort.Direction.ASC,orderName);
            return sort;
        }
        return null;
    }


    //提供getter setter

    /**
     * 解决前台首页为1,后台首页为0的问题
     * @return 返回当前页码
     */
    public int getJpaPage() {
        return currentPage-1;
    }

    /**
     * @return 返回当前页码(未处理前台)
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage 设置当前页码
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    /**
     * @return 获取分页显示条数
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize 设置分页显示条数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    //兼容easyui分页
    public void setPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public void setRows(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return 返回排序类型
     * orderName有值才做排序
     * Sort sort = new Sort(orderType?Sort.Direction.DESC:Sort.Direction.ASC,orderName);
     */
    public boolean isOrderType() {
        return orderType;
    }

    /**
     * @param orderType 设置排序类型
     * orderType?Sort.Direction.DESC:Sort.Direction.ASC
     */
    public void setOrderType(boolean orderType) {
        this.orderType = orderType;
    }

    /**
     * @return 获取按照排序名的属性
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * @param orderName 设置按照排序的属性
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
