package com.nassu.bean;

public class ShoppingItem {
	private String siid;
	private Integer count;
	private Double total;
	private String uid;
	private String bid;
	private String oid;
	private Book book;
	
	public String getSiid() {
		return siid;
	}
	public void setSiid(String siid) {
		this.siid = siid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String toString() {
		return "ShoppingItem [siid=" + siid + ", count=" + count + ", total=" + total + ", uid=" + uid + ", bid=" + bid
				+ ", oid=" + oid + "]";
	}
}
