package com.test.dashboard.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class HttpRequestWrapperFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(HttpRequestWrapperFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("[Filter] : HttpRequestWrapperFilter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpRequestWrapper wrapper = new HttpRequestWrapper((HttpServletRequest)request);
			wrapper.setAttribute("requestBody", wrapper.getRequestBody());
			chain.doFilter(wrapper, response);
		} catch (Exception e) {
			chain.doFilter(request, response);
		}
		

		// TODO Auto-generated method stub

	}

}
