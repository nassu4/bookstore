package com.nassu.mapper;

import java.util.List;

import com.nassu.bean.Category;

public interface CategoryMapper {
	void add(Category category);
	void delete(Category category);
	void modify(Category category);
	Category readById(String cid);
	List<Category> findAll();
	long count();
	Category readByName(String name);
}
