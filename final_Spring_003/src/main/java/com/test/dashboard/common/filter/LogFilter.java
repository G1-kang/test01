package com.test.dashboard.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFilter implements Filter{

	
	private Logger logger = LoggerFactory.getLogger(LogFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO LogFilter init
		logger.info("[Filter] : LogFilter init");
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO LogFilter doFilter
		HttpServletRequest req = (HttpServletRequest) request;
		logger.info("[Filter] ServletContextPath : " + request.getServletContext().getContextPath());
		logger.info("[Filter] ServletContextRealPath : " + request.getServletContext().getRealPath("/"));
		logger.info("[Filter] URI : " + req.getRequestURI());
		logger.info("[Filter] URL : " + req.getRequestURL());
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		// TODO LogFilter destroy
	}
	
}
