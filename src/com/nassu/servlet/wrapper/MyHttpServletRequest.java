package com.nassu.servlet.wrapper;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyHttpServletRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	
	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	public String getParameter(String name) {
		String method = request.getMethod();
		if (method.equalsIgnoreCase("get")) {
			try {
				String value = request.getParameter(name);
				value = new String(value.getBytes("ISO8859-1"), "UTF-8");
				return value;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else if (method.equalsIgnoreCase("post")) {
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return super.getParameter(name);
	}

	public Map<String, String[]> getParameterMap() {
		String method = request.getMethod();
		if (method.equalsIgnoreCase("get")) {
			Map<String, String[]> map = request.getParameterMap();
			Set<String> names = map.keySet();
			for (String name : names) {
				try {
					name = new String(name.getBytes("ISO8859-1"), "UTF-8");
					String[] values = map.get(name);
					for (String value : values) {
						value = new String(value.getBytes("ISO8859-1"), "UTF-8");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return map;
		} else if (method.equalsIgnoreCase("post")) {
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return super.getParameterMap();
	}
}
