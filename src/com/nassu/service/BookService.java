package com.nassu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nassu.bean.Book;
import com.nassu.mapper.BookMapper;

@Service
public class BookService {
	@Autowired
	private BookMapper bookMapper;
	

	public List<Book> readByCid(String cid) {
		return bookMapper.readByCid(cid);
	}

	public Book readByBid(String bid) {
		return bookMapper.readById(bid);
	}

	public List<Book> findAllBook() {
		return bookMapper.findAllBook();
	}

	public void deleteByCid(String cid) {
		bookMapper.deleteByCid(cid);
	}

	public long count() {
		return bookMapper.count();
	}

	public void add(Book book) {
		bookMapper.add(book);
	}

	public boolean imageExists(String image) {
		Book book = bookMapper.readByImage(image);
		if (book != null)
			return true;
		else
			return false;
	}

	public void modify(Book book) {
		bookMapper.modify(book);
	}

	public void deleteByBid(String bid) {
		Book book = bookMapper.readById(bid);
		bookMapper.delete(book);
	}
}
