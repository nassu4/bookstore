package com.nassu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nassu.bean.User;
import com.nassu.service.OrderService;
import com.nassu.service.ShoppingItemService;
import com.nassu.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("user_regist.do")
	public ModelAndView regist(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = populate(request);
		boolean flag = userService.regist(user);
		if (flag) {
			request.getSession().setAttribute("user", user);
			mav.setViewName("/jsps/main.jsp");
		} else {
			request.setAttribute("msg", "用户名已被注册");
			mav.setViewName("/jsps/user/regist.jsp");
		}
		return mav;
	}
	
	@RequestMapping("user_login.do")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (request.getParameter("check_code").equals(request.getSession().getAttribute("checkCode"))) {
			User user = populate(request);
			User u = userService.login(user);
			if (u != null) {
				if (!u.getStatus().equals("0")) {
					request.getSession().setAttribute("user", user);
					mav.setViewName("/jsps/main.jsp");
					return mav;
				} else {
					request.setAttribute("msg", "账号已被冻结");
				}
			} else {
				request.setAttribute("msg", "用户名或密码错误");
			}
		} else {
			request.setAttribute("msg", "验证码错误");
		}
		mav.setViewName("/jsps/user/login.jsp");
		return mav;
	}
	
	@RequestMapping("user_exit.do")
	public ModelAndView exit(HttpSession sess) {
		ModelAndView mav = new ModelAndView();
		sess.removeAttribute("user");
		mav.setViewName("/jsps/main.jsp");
		return mav;
	}
	
	@RequestMapping("user_admin.do")
	public ModelAndView admin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = populate(request);
		if (user.getName().equals("admin") && user.getPassword().equals("123456")) {
			request.getSession().setAttribute("admin", user);
			mav.setViewName("/adminjsps/admin/index.jsp");
		} else {
			mav.addObject("msg", "管理员账号名或密码错误");
			mav.setViewName("/adminjsps/login.jsp");
		}
		return mav;
	}
	
	@RequestMapping("user_findAllUser.do")
	public ModelAndView findAllUser() {
		ModelAndView mav = new ModelAndView();
		List<User> list = userService.findAllUser();
		mav.addObject("list", list);
		mav.setViewName("/adminjsps/admin/user/list.jsp");
		return mav;
	}
	
	@RequestMapping("user_changeStatus.do")
	public ModelAndView changeStatus(HttpServletRequest request) {
		String uid = request.getParameter("uid");
		User user = userService.readByUid(uid);
		if (user.getStatus().equals("0")) {
			user.setStatus("1");
		} else {
			user.setStatus("0");
		}
		userService.modify(user);
		return findAllUser();
	}
	
	@RequestMapping("user_delete.do")
	public ModelAndView delete(HttpServletRequest request) {
		String uid = request.getParameter("uid");
		User user = userService.readByUid(uid);
		ShoppingItemService shoppingItemService = new ShoppingItemService();
		shoppingItemService.clearByUid(uid);
		OrderService orderService = new OrderService();
		orderService.clearByUid(uid);
		userService.removeUser(user);
		return findAllUser();
	}
	
	@RequestMapping("user_addUser.do")
	public ModelAndView addUser(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = populate(request);
		boolean flag = userService.regist(user);
		if (flag) {
			return findAllUser();
		} else {
			mav.addObject("msg", "用户名已存在");
			mav.setViewName("/adminjsps/admin/user/add.jsp");
			return mav;
		}
	}
	
	@RequestMapping("user_modifyPassword.do")
	public ModelAndView modifyPassword(HttpServletRequest request) {
		User user = populate(request);
		User oldUser = userService.readByName(user.getName());
		oldUser.setPassword(user.getPassword());
		userService.modify(oldUser);
		return findAllUser();
	}
	
	private User populate(HttpServletRequest request) {
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
