package com.nassu.bean;

import java.util.List;

public class Order {
	private String oid;
	private String time;
	private Double total;
	private Integer state;//0：未付款、1：待发货、2：待收货、3：订单完成
	private String address;
	private String uid;
	private List<ShoppingItem> list;
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public List<ShoppingItem> getList() {
		return list;
	}
	public void setList(List<ShoppingItem> list) {
		this.list = list;
	}
	public String toString() {
		return "Order [oid=" + oid + ", time=" + time + ", total=" + total + ", state=" + state + ", address=" + address
				+ ", uid=" + uid + "]";
	}
}
