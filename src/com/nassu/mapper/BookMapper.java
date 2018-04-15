package com.nassu.mapper;

import java.util.List;

import com.nassu.bean.Book;
import com.nassu.bean.Category;

public interface BookMapper {
	void cidSetNull(Category category);
	void add(Book book);
	void delete(Book book);
	void modify(Book book);
	Book readById(String bid);
	List<Book> readByCid(String cid);
	List<Book> findAllBook();
	void deleteByCid(String cid);
	long count();
	Book readByImage(String image);
}
