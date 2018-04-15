package com.nassu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nassu.bean.Category;
import com.nassu.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("category_findAllCategory.do")
	public ModelAndView findAllCategory() {
		ModelAndView mav = new ModelAndView();
		List<Category> list = categoryService.findAllCategory();
		mav.addObject("list", list);
		mav.setViewName("/jsps/left.jsp");
		return mav;
	}
	
	@RequestMapping("category_findAllCategory2.do")
	public ModelAndView findAllCategory2() {
		ModelAndView mav = new ModelAndView();
		List<Category> list = categoryService.findAllCategory();
		mav.addObject("list", list);
		mav.setViewName("/adminjsps/admin/category/list.jsp");
		return mav;
	}
	
	@RequestMapping("category_addCategory.do")
	public ModelAndView addCategory(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String name = request.getParameter("name");
		Category category = categoryService.readByName(name);
		if (category == null) {
			categoryService.addByName(name);
			return findAllCategory2();
		} else {
			mav.addObject("msg", "该分类名已存在");
			mav.setViewName("/adminjsps/admin/category/add.jsp");
			return mav;
		}
	}
	
	@RequestMapping("category_modify.do")
	public ModelAndView modify(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String oname = request.getParameter("oname");
		String nname = request.getParameter("nname");
		Category ocategory = categoryService.readByName(oname);
		Category ncategory = categoryService.readByName(nname);
		if (ncategory == null) {
			ocategory.setName(nname);
			categoryService.modify(ocategory);
			return findAllCategory2();
		} else {
			mav.addObject("msg", "该分类名已存在");
			mav.setViewName("/adminjsps/admin/category/mod.jsp");
			return mav;
		}
	}
	
	@RequestMapping("category_removeCategory.do")
	public ModelAndView removeCategory(HttpServletRequest request) {
		String cid = request.getParameter("cid");
		Category category = categoryService.readByCid(cid);
		categoryService.delete(category);
		return findAllCategory2();
	}
}
