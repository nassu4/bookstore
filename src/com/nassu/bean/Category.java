package com.nassu.bean;

public class Category {
	private String cid;
	private String name;
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return "Category [cid=" + cid + ", name=" + name + "]";
	}
}
