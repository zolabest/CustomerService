package com.complaint.models;

public class Complaint {
	int id;
	int userId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	String name;
	String address;
	String pinCode;
	String telephone;
	String typeOfProblem;
	String status;
	int assigneeId;
	/*
	 CREATE TABLE complaint (
	 	id INT PRIMARY KEY AUTO_INCREMENT,
	 	userId INT,
	 	name VARCHAR(255),
	 	address VARCHAR(255),
	 	pinCode VARCHAR(255),
	 	type_of_problem VARCHAR(255),
	 	status VARCHAR(255),
	 	assignee_id INT
	  );
	 (id,userId,name,address,pinCode, type_of_problem, status, assignee_id)
	 */
	
	public Complaint() {
		
	}
	public Complaint(String name, String address,
			String pinCode, String telephone,
			String typeOfProblem,
			String status) {
		super();
		this.name = name;
		this.address = address;
		this.pinCode = pinCode;
		this.telephone = telephone;
		this.typeOfProblem = typeOfProblem;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Complaint [id=" + id + ", userId=" + userId + ", name=" + name + ", address=" + address + ", pinCode="
				+ pinCode + ", telephone=" + telephone + ", typeOfProblem=" + typeOfProblem + ", status=" + status
				+ ", assigneeId=" + assigneeId + "]";
	}
	public void setId (int id)
	{
		this.id=id;
	}
	public int getId () {
		return id;
	}
	public int getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTypeOfProblem() {
		return typeOfProblem;
	}
	public void setTypeOfProblem(String typeOfProblem) {
		this.typeOfProblem = typeOfProblem;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
