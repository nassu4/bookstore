package com.nassu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nassu.bean.Book;
import com.nassu.bean.ShoppingItem;
import com.nassu.bean.User;
import com.nassu.service.BookService;
import com.nassu.service.ShoppingItemService;
import com.nassu.service.UserService;

@Controller
public class ShoppingCartController {
	@Autowired
	private ShoppingItemService shoppingItemService;
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	
	@RequestMapping("shoppingCart_addShoppingItem.do")
	public ModelAndView addShoppingItem(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.getSession().setAttribute("msg", "ÇëÏÈµÇÂ¼");
			mav.setViewName("/bookstore/jsps/user/login.jsp");
			return mav;
		} else {
			user = userService.readByName(user.getName());
			String bid = request.getParameter("bid");
			Book book = bookService.readByBid(bid);
			String count = request.getParameter("count");
			ShoppingItem item = shoppingItemService.findShoppingItemInCart(bid, user.getUid());
			if (item != null) {
				shoppingItemService.updateShoppingItem(book, item, count);
			} else {
				shoppingItemService.addShoppingItem(book, count, user.getUid());
			}
			return findAllShoppingItem(request);
		}
	}
	
	@RequestMapping("shoppingCart_findAllShoppingItem.do")
	public ModelAndView findAllShoppingItem(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getSession().getAttribute("user");
		user = userService.readByName(user.getName());
		List<ShoppingItem> list = shoppingItemService.findShoppingItemByUid(user.getUid());
		mav.addObject("list", list);
		mav.setViewName("/jsps/cart/list.jsp");
		return mav;
	}
	
	@RequestMapping("shoppingCart_clear.do")
	public ModelAndView clear(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		user = userService.readByName(user.getName());
		shoppingItemService.clearInCart(user.getUid());
		return findAllShoppingItem(request);
	}
	
	@RequestMapping("shoppingCart_removeShoppingItem.do")
	public ModelAndView removeShoppingItem(HttpServletRequest request) {
		String siid = request.getParameter("siid");
		shoppingItemService.removeShoppingItem(siid);
		return findAllShoppingItem(request);
	}
}
