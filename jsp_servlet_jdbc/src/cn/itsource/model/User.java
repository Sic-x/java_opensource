package cn.itsource.model;

public class User {
	private String username;
	private String password;
	private String nickname;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String username, String password, String nickname) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}
	public User() {
	}
	@Override
	public String toString() {
		return "用户名:" + username + ", 密码:" + password 
				+ ", 昵称:" + nickname;
	}		
}
