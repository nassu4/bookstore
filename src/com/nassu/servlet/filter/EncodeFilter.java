package com.nassu.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.nassu.servlet.wrapper.MyHttpServletRequest;

public class EncodeFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		MyHttpServletRequest myRequest = new MyHttpServletRequest((HttpServletRequest) request);
		response.setContentType("text/html;charset=UTF-8");
		chain.doFilter(myRequest, response);
	}

	public void destroy() {
	}
}
