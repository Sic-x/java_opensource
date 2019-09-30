package cn.itsource.util;

public class JobsCondition {
	//职位名称
	private String title;
	//职位类型
	private Integer positiontype;
	//封装条件方法
	public static String getCondition(String title,Integer positiontype){
		//SELECT * FROM VIEW_JOBS_CITY WHERE ISENABLED = 1
		String sql = " ";
		if(title!=null && !title.trim().equals("")){
			sql += "AND TITLE LIKE '%"+title+"%' ";
		}
		if(positiontype!=null){//数值型的数据类型只需要判断null值
			sql += "AND POSITIONTYPE = "+positiontype;
		}
		return sql;
	}

	public JobsCondition() {
	}

	public JobsCondition(String title, Integer positiontype) {
		this.title = title;
		this.positiontype = positiontype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPositiontype() {
		return positiontype;
	}

	public void setPositiontype(Integer positiontype) {
		this.positiontype = positiontype;
	}

	@Override
	public String toString() {
		return "JobsCondition [title=" + title + ", positiontype=" + positiontype + "]";
	}
	
	
}
