package com.nassu.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nassu.bean.User;
import com.nassu.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public boolean regist(User user) {
		if (userMapper.readByName(user.getName()) != null) {
			return false;
		} else {
			UUID uuid = UUID.randomUUID();
			user.setUid(uuid.toString());
			user.setStatus("1");
			userMapper.add(user);
			return true;
		}
	}

	public User login(User user) {
		return userMapper.login(user);
	}

	public User readByName(String name) {
		return userMapper.readByName(name);
	}

	public List<User> findAllUser() {
		return userMapper.findAllUser();
	}

	public User readByUid(String uid) {
		return userMapper.readById(uid);
	}

	public void modify(User user) {
		userMapper.modify(user);
	}

	public void removeUser(User user) {
		userMapper.delete(user);
	}
}
