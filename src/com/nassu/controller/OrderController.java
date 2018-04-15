package com.nassu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nassu.bean.Order;
import com.nassu.bean.ShoppingItem;
import com.nassu.bean.User;
import com.nassu.service.OrderService;
import com.nassu.service.ShoppingItemService;
import com.nassu.service.UserService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private ShoppingItemService shoppingItemService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("order_createOrder.do")
	public ModelAndView createOrder(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String uid = request.getParameter("uid");
		List<ShoppingItem> list = shoppingItemService.findShoppingItemByUid(uid);
		Order order = orderService.createOrder(list);
		shoppingItemService.addIntoOrder(uid, order.getOid());
		mav.addObject("order", order);
		mav.setViewName("/jsps/order/desc.jsp");
		return mav;
	}
	
	@RequestMapping("order_findAllOrder.do")
	public ModelAndView findAllOrder(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String name = request.getParameter("name");
		return findAllOrderByName(mav, name);
	}
	
	@RequestMapping("order_pay.do")
	public void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String address = request.getParameter("address");
		String oid = request.getParameter("oid");
		orderService.pay(oid, address);
		response.getWriter().write("<h1 style='color: red;'>付款成功！3秒后回到首页</h1>");
		response.setHeader("Refresh", "3;url=/bookstore/index.jsp");
	}
	
	@RequestMapping("order_toPay.do")
	public ModelAndView toPay(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String oid = request.getParameter("oid");
		Order order = orderService.readByOid(oid);
		mav.addObject("order", order);
		mav.setViewName("/jsps/order/desc.jsp");
		return mav;
	}
	
	@RequestMapping("order_findAllOrder2.do")
	public ModelAndView findAllOrder2() {
		ModelAndView mav = new ModelAndView();
		List<Order> list = orderService.findAllOrder();
		mav.addObject("list", list);
		mav.setViewName("/adminjsps/admin/order/list.jsp");
		return mav;
	}
	
	@RequestMapping("order_toDeliver.do")
	public ModelAndView toDeliver(HttpServletRequest request) {
		String oid = request.getParameter("oid");
		orderService.toDeliver(oid);
		return findAllOrder2();
	}
	
	@RequestMapping("order_toReceive.do")
	public ModelAndView toReceive(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String oid = request.getParameter("oid");
		Order order = orderService.toReceive(oid);
		User user = userService.readByUid(order.getUid());
		return findAllOrderByName(mav, user.getName());
	}
	
	@RequestMapping("order_findOrderByState.do")
	public ModelAndView findOrderByState(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String state = request.getParameter("state");
		List<Order> list = orderService.findOrderByState(Integer.parseInt(state));
		mav.addObject("list", list);
		mav.setViewName("/adminjsps/admin/order/list.jsp");
		return mav;
	}
	
	private ModelAndView findAllOrderByName(ModelAndView mav, String name) {
		User user = userService.readByName(name);
		List<Order> list = orderService.findAllOrder(user.getUid());
		mav.addObject("list", list);
		mav.setViewName("/jsps/order/list.jsp");
		return mav;
	}
}
