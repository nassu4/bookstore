package com.nassu.bean;

public class User {
	private String uid;//用id代替购物车实体
	private String name;
	private String password;
	private String address;
	private String status;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", password=" + password + ", address=" + address + ", status="
				+ status + "]";
	}
}
