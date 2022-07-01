package com.complaint.models;

public class User {
	int id;
	String username;
	String password;
	
	int roleId;
	int status;
	

	public User(int id, String username,
			String password,  
			int roleId, int status) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;

		this.roleId = roleId;
		this.status = status;
	}
	public User() {
		id=0;
		//roleId=1;
		status=1;
	}
	public User(String username) {
		super();
		this.username = username;
	}
	
	
	@Override
	public String toString() {
		return "{id:" + id + ", username:'" +
				username + "',  roleId:" + roleId
				+ ", status:'" + status + "'}";
	}
	public void setId(int id) {this.id=id;}
	public int getId() {return id;}
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
	
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
