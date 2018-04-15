package com.nassu.mapper;

import java.util.List;

import com.nassu.bean.User;

public interface UserMapper {
	void add(User user);
	void delete(User user);
	void modify(User user);
	User readById(String uid);
	User readByName(String name);
	User login(User user);
	List<User> findAllUser();
}
