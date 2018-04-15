package com.nassu.mapper;

import java.util.List;

import com.nassu.bean.Order;
import com.nassu.bean.User;

public interface OrderMapper {
	void deleteByUid(User user);
	void add(Order order);
	void modify(Order order);
	Order readById(String oid);
	List<Order> readByUidDesc(String uid);
	List<Order> findAllByDesc();
	List<Order> findByStateDesc(int state);
}
