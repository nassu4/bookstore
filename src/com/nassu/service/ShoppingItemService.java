package com.nassu.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nassu.bean.Book;
import com.nassu.bean.ShoppingItem;
import com.nassu.mapper.ShoppingitemMapper;

@Service
public class ShoppingItemService {
	@Autowired
	private ShoppingitemMapper shoppingitemMapper;

	public List<ShoppingItem> findShoppingItemByUid(String uid) {
		return shoppingitemMapper.readByUidInCart(uid);
	}

	public void addShoppingItem(Book book, String count, String uid) {
		ShoppingItem item = new ShoppingItem();
		item.setBid(book.getBid());
		item.setCount(Integer.parseInt(count));
		item.setSiid(UUID.randomUUID().toString());
		item.setTotal(book.getPrice() * Integer.parseInt(count));
		item.setUid(uid);
		shoppingitemMapper.add(item);
	}

	public ShoppingItem findShoppingItemInCart(String bid, String uid) {
		ShoppingItem item = new ShoppingItem();
		item.setBid(bid);
		item.setUid(uid);
		return shoppingitemMapper.findShoppingItemInCart(item);
	}

	public void updateShoppingItem(Book book, ShoppingItem item, String count) {
		item.setCount(item.getCount() + Integer.parseInt(count));
		item.setTotal(item.getCount() * book.getPrice());
		shoppingitemMapper.modify(item);
	}

	public void clearInCart(String uid) {
		shoppingitemMapper.clearInCart(uid);
	}

	public void removeShoppingItem(String siid) {
		ShoppingItem item = shoppingitemMapper.readById(siid);
		shoppingitemMapper.delete(item);
	}

	public void addIntoOrder(String uid, String oid) {
		ShoppingItem item = new ShoppingItem();
		item.setUid(uid);
		item.setOid(oid);
		shoppingitemMapper.addIntoOrder(item);
	}

	public void clearByUid(String uid) {
		shoppingitemMapper.clearByUid(uid);
	}
}
