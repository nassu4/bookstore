package com.nassu.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nassu.bean.Order;
import com.nassu.bean.ShoppingItem;
import com.nassu.mapper.OrderMapper;
import com.nassu.mapper.ShoppingitemMapper;

@Service
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ShoppingitemMapper shoppingitemMapper;

	public Order createOrder(List<ShoppingItem> list) {
		Order order = new Order();
		order.setOid(UUID.randomUUID().toString());
		Date date = new Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(date);
		order.setTime(time);
		order.setState(0);
		double total = 0;
		for (ShoppingItem item : list) {
			total += item.getTotal();
		}
		order.setTotal(total);
		order.setUid(list.get(0).getUid());
		order.setList(list);
		orderMapper.add(order);
		return order;
	}
	
	public void pay(String oid, String address) {
		Order order = readByOid(oid);
		order.setState(1);
		order.setAddress(address);
		modify(order);
	}

	public List<Order> findAllOrder(String uid) {
		return orderMapper.readByUidDesc(uid);
	}

	public Order readByOid(String oid) {
		Order order = orderMapper.readById(oid);
		List<ShoppingItem> list = shoppingitemMapper.readByOid(oid);
		order.setList(list);
		return order;
	}
	
	public void toDeliver(String oid) {
		Order order = readByOid(oid);
		order.setState(2);
		modify(order);
	}
	
	public Order toReceive(String oid) {
		Order order = readByOid(oid);
		order.setState(3);
		modify(order);
		return order;
	}

	public void modify(Order order) {
		orderMapper.modify(order);
	}

	public void clearByUid(String uid) {
		throw new UnsupportedOperationException("Unsupported In This Version");
	}

	public List<Order> findAllOrder() {
		return orderMapper.findAllByDesc();
	}

	public List<Order> findOrderByState(int state) {
		return orderMapper.findByStateDesc(state);
	}
}
