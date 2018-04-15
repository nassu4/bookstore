package com.nassu.bean;

public class Book {
	private String bid;
	private String name;
	private Double price;
	private String author;
	private String image;
	private String cid;
	
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String toString() {
		return "Book [bid=" + bid + ", name=" + name + ", price=" + price + ", author=" + author + ", image=" + image
				+ ", cid=" + cid + "]";
	}
}
