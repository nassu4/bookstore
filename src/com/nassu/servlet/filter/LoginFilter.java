package com.nassu.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getSession().getAttribute("user") != null) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("msg", "ÇëÏÈµÇÂ¼");
			request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
		}
	}

	public void destroy() {
	}
}
