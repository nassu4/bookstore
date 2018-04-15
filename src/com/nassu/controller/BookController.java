package com.nassu.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nassu.bean.Book;
import com.nassu.bean.Category;
import com.nassu.service.BookService;
import com.nassu.service.CategoryService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("book_findAllBook.do")
	public ModelAndView findAllBook() {
		ModelAndView mav = new ModelAndView();
		List<Book> list = bookService.findAllBook();
		mav.addObject("list", list);
		mav.setViewName("/jsps/book/list.jsp");
		return mav;
	}
	
	@RequestMapping("book_readByCid.do")
	public ModelAndView readByCid(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String cid = request.getParameter("cid");
		List<Book> list = bookService.readByCid(cid);
		mav.addObject("list", list);
		mav.setViewName("/jsps/book/list.jsp");
		return mav;
	}
	
	@RequestMapping("book_readByBid.do")
	public ModelAndView readByBid(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bid = request.getParameter("bid");
		Book book = bookService.readByBid(bid);
		mav.addObject("book", book);
		mav.setViewName("/jsps/book/desc.jsp");
		return mav;
	}
	
	@RequestMapping("book_findAllBook2.do")
	public ModelAndView findAllBook2() {
		ModelAndView mav = new ModelAndView();
		List<Book> list = bookService.findAllBook();
		mav.addObject("list", list);
		mav.setViewName("/adminjsps/admin/book/list.jsp");
		return mav;
	}
	
	@RequestMapping("book_toModifyPage.do")
	public ModelAndView toModifyPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bid = request.getParameter("bid");
		Book book = bookService.readByBid(bid);
		List<Category> list = categoryService.findAllCategory();
		mav.addObject("book", book);
		mav.addObject("list", list);
		mav.setViewName("/adminjsps/admin/book/desc.jsp");
		return mav;
	}
	
	@RequestMapping("book_toAddPage.do")
	public ModelAndView toAddPage() {
		ModelAndView mav = new ModelAndView();
		List<Category> list = categoryService.findAllCategory();
		mav.addObject("list", list);
		mav.setViewName("/adminjsps/admin/book/add.jsp");
		return mav;
	}
	
	@RequestMapping("book_modify.do")
	public ModelAndView modify(HttpServletRequest request) {
		Book book = new Book();
		try {
			BeanUtils.populate(book, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		bookService.modify(book);
		return findAllBook2();
	}
	
	@RequestMapping("book_delete.do")
	public ModelAndView delete(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bid = request.getParameter("bid");
		bookService.deleteByBid(bid);
		mav.setViewName("/adminjsps/admin/book/add.jsp");
		return mav;
	}
	
	@RequestMapping("book_bookUpload.do")
	public ModelAndView bookUpload(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		BookService service = bookService;
		Map<String, Object> map = new HashMap<String, Object>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("UTF-8");
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list = sfu.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String key = item.getFieldName();
					String value= item.getString("UTF-8");
					map.put(key, value);
				} else {
					String fileName = item.getName();
					int pos = fileName.indexOf("\\");
					if (pos != -1) {
						fileName = fileName.substring(pos + 1);
					}
					String image = "book_img/" + fileName;
					if (service.imageExists(image)) {
						throw new FileUploadException("图片名存在");
					}
					map.put("image", image);
					BufferedInputStream in1 = new BufferedInputStream(item.getInputStream());
					BufferedInputStream in2 = new BufferedInputStream(item.getInputStream());
					BufferedOutputStream out1 = new BufferedOutputStream(new FileOutputStream("F:\\myeclipse-workspace\\bookstore\\WebRoot\\book_img\\" + fileName));
					BufferedOutputStream out2 = new BufferedOutputStream(new FileOutputStream(request.getServletContext().getRealPath("/book_img") + "/" + fileName));
					byte[] buf = new byte[1024];
					for (int len = 0; (len = in1.read(buf)) != -1;) {
						out1.write(buf, 0, len);
					}
					for (int len = 0; (len = in2.read(buf)) != -1;) {
						out2.write(buf, 0, len);
					}
					out1.close();
					out2.close();
				}
			}
			Book book = new Book();
			BeanUtils.populate(book, map);
			long count = service.count();
			book.setBid(String.valueOf(count + 1));
			service.add(book);
		} catch (FileUploadException e) {
			e.printStackTrace();
			mav.addObject("msg", "上传失败(可能图片名已存在)");
			mav.setViewName("/adminjsps/admin/book/add.jsp");
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return findAllBook2();
	}
}
