package com.nassu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nassu.bean.Category;
import com.nassu.mapper.CategoryMapper;

@Service
public class CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;

	public List<Category> findAllCategory() {
		return categoryMapper.findAll();
	}

	public long count() {
		return categoryMapper.count();
	}

	public Category readByName(String name) {
		return categoryMapper.readByName(name);
	}

	public void add(Category category) {
		categoryMapper.add(category);
	}
	
	public void addByName(String name) {
		long count = count();
		Category category = new Category();
		category.setName(name);
		category.setCid(String.valueOf(count + 1));
		add(category);
	}

	public void modify(Category category) {
		categoryMapper.modify(category);
	}

	public Category readByCid(String cid) {
		return categoryMapper.readById(cid);
	}

	public void delete(Category category) {
		categoryMapper.delete(category);
	}
}
