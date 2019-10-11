package cn.itsource.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Imgs {
	private Integer imgid;				//图片ID
	private String storepath;			//存储路径
	private String storename;			//存储名称
	private String intro;				//简介
	private Boolean isenabled;			//是否启用：决定前台是否展示
	private Date inputdate=new Date();	//上传时间
	private MultipartFile fileImg;		//复杂表单
	public Imgs() {
	}
	public Imgs(String storepath, String storename, String intro, Boolean isenabled, Date inputdate,
			MultipartFile fileImg) {
		this.storepath = storepath;
		this.storename = storename;
		this.intro = intro;
		this.isenabled = isenabled;
		this.inputdate = inputdate;
		this.fileImg = fileImg;
	}
	public Imgs(Integer imgid, String storepath, String storename, String intro, Boolean isenabled, Date inputdate,
			MultipartFile fileImg) {
		this.imgid = imgid;
		this.storepath = storepath;
		this.storename = storename;
		this.intro = intro;
		this.isenabled = isenabled;
		this.inputdate = inputdate;
		this.fileImg = fileImg;
	}
	public Integer getImgid() {
		return imgid;
	}
	public void setImgid(Integer imgid) {
		this.imgid = imgid;
	}
	public String getStorepath() {
		return storepath;
	}
	public void setStorepath(String storepath) {
		this.storepath = storepath;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
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
	public MultipartFile getFileImg() {
		return fileImg;
	}
	public void setFileImg(MultipartFile fileImg) {
		this.fileImg = fileImg;
	}
	@Override
	public String toString() {
		return "Imgs [imgid=" + imgid + ", storepath=" + storepath + ", storename=" + storename + ", intro=" + intro
				+ ", isenabled=" + isenabled + ", inputdate=" + inputdate + ", fileImg=" + fileImg + "]";
	}
}