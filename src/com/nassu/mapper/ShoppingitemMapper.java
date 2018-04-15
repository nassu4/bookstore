package com.nassu.mapper;

import java.util.List;

import com.nassu.bean.Book;
import com.nassu.bean.ShoppingItem;
import com.nassu.bean.User;

public interface ShoppingitemMapper {
	void bidSetNull(Book book);
	void deleteByUid(User user);
	void add(ShoppingItem item);
	void delete(ShoppingItem item);
	void modify(ShoppingItem item);
	ShoppingItem readById(String siid);
	List<ShoppingItem> readByUidInCart(String uid);
	ShoppingItem findShoppingItemInCart(ShoppingItem item);
	void clearInCart(String uid);
	void addIntoOrder(ShoppingItem item);
	List<ShoppingItem> readByOid(String oid);
	void clearByUid(String uid);
}
