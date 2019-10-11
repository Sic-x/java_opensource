package cn.itsource.domain;

public class City {
	private	Integer id;
	private	String cname;
	public City() {
	}
	
	public City(Integer id, String cname) {
		super();
		this.id = id;
		this.cname = cname;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public City(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", cname=" + cname + "]";
	}
	
}
