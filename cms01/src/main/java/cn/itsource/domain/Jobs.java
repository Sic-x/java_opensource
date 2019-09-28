package cn.itsource.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Jobs implements Serializable{
	//构造方法
	public Jobs() {
	}
	public Jobs(String title, String address, Integer jobnum, Integer treatment, String describes, String requires,
			String htmlurl, Integer positiontype, Boolean isenabled, Date inputdate) {
		this.title = title;
		this.address = address;
		this.jobnum = jobnum;
		this.treatment = treatment;
		this.describes = describes;
		this.requires = requires;
		this.htmlurl = htmlurl;
		this.positiontype = positiontype;
		this.isenabled = isenabled;
		this.inputdate = inputdate;
	}
	public Jobs(Integer id, String title, String address, Integer jobnum, Integer treatment, String describes,
			String requires, String htmlurl, Integer positiontype, Boolean isenabled, Date inputdate) {
		this.id = id;
		this.title = title;
		this.address = address;
		this.jobnum = jobnum;
		this.treatment = treatment;
		this.describes = describes;
		this.requires = requires;
		this.htmlurl = htmlurl;
		this.positiontype = positiontype;
		this.isenabled = isenabled;
		this.inputdate = inputdate;
	}
	// 职位编号
	private Integer id;
	// 职位名称
	private String title;
	// 工作地点
	private String address;
	// 招聘人数
	private Integer jobnum;
	// 待遇
	private Integer treatment;
	// 职位描述
	private String describes;
	// 职位要求
	private String requires;
	// 链接职位详情
	private String htmlurl;
	// 职位类型
	private Integer positiontype;
	// 是否启用
	private Boolean isenabled;
	// 职位发布时间
	@DateTimeFormat
	private Date inputdate = new Date();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getJobnum() {
		return jobnum;
	}
	public void setJobnum(Integer jobnum) {
		this.jobnum = jobnum;
	}
	public Integer getTreatment() {
		return treatment;
	}
	public void setTreatment(Integer treatment) {
		this.treatment = treatment;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public String getRequires() {
		return requires;
	}
	public void setRequires(String requires) {
		this.requires = requires;
	}
	public String getHtmlurl() {
		return htmlurl;
	}
	public void setHtmlurl(String htmlurl) {
		this.htmlurl = htmlurl;
	}
	public Integer getPositiontype() {
		return positiontype;
	}
	public void setPositiontype(Integer positiontype) {
		this.positiontype = positiontype;
	}
	public Boolean getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(Boolean isenabled) {
		this.isenabled = isenabled;
	}
	public Date getInputdate() {
		return inputdate;
	}
	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}
	@Override
	public String toString() {
		return "Jobs [id=" + id + ", title=" + title + ", address=" + address + ", jobnum=" + jobnum + ", treatment="
				+ treatment + ", describes=" + describes + ", requires=" + requires + ", htmlurl=" + htmlurl
				+ ", positiontype=" + positiontype + ", isenabled=" + isenabled + ", inputdate=" + inputdate + "]";
	}

}
