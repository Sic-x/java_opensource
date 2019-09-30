package cn.itsource.util;

import java.util.List;

public class PageBeanUtil<T> {
	// 当前页
	private Integer localPage;
	// 总页数
	private Integer totalPage;
	// 每页显示数量
	private Integer pageSize = 5;
	// 总数据量
	private Integer totalNum;
	// 首页
	private Integer firstPage = 1;
	// 上一页
	private Integer prePage;
	// 下一页
	private Integer nextPage;
	// 尾页
	private Integer lastPage;
	// 显示的数据
	private List<T> list;

	public PageBeanUtil() {
	}

	// 传递localPage和totalNum就可以计算totalPage,prePage,nextPage,lastPage
	public PageBeanUtil(Integer localPage, Integer totalNum) {
		this.localPage = localPage;// 前台传递,需要通过Controller获取
		this.totalNum = totalNum;// 通过数据库查询
		this.totalPage = this.totalNum % this.pageSize == 0 ? this.totalNum / this.pageSize
				: this.totalNum / this.pageSize + 1;
		this.prePage = this.localPage == 1 ? 1 : this.localPage - 1;
		this.nextPage = this.localPage == this.totalPage ? this.totalPage : this.localPage + 1;
		this.lastPage = this.totalPage;
	}

	public Integer getLocalPage() {
		return localPage;
	}

	public void setLocalPage(Integer localPage) {
		this.localPage = localPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(Integer firstPage) {
		this.firstPage = firstPage;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBeanUtil [localPage=" + localPage + ", totalPage=" + totalPage + ", pageSize=" + pageSize
				+ ", totalNum=" + totalNum + ", firstPage=" + firstPage + ", prePage=" + prePage + ", nextPage="
				+ nextPage + ", lastPage=" + lastPage + ", list=" + list + "]";
	}

}
