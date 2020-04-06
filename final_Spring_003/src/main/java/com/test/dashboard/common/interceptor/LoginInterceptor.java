package com.test.dashboard.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.test.dashboard.model.dto.MemberDto;

public class LoginInterceptor implements HandlerInterceptor{

	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("[requestMethod] : " + request.getMethod());
		logger.info("[requestURI] : "+ request.getRequestURI());
		
		HttpSession session = request.getSession();
		MemberDto user = (MemberDto) session.getAttribute("user");
		
		if(request.getRequestURI().endsWith("login")) {
			
			if(user == null) return true;
			else response.sendRedirect("/dashboard");
			
		} else if(request.getRequestURI().endsWith("idsearch")) return true;
		else if(request.getRequestURI().endsWith("nickcheck")) return true;
		else if(request.getRequestURI().endsWith("idcheck")) return true;
		else if(request.getRequestURI().endsWith("pwmodify")) return true;
		else if(request.getRequestURI().endsWith("signup")) return true;
		else if(request.getRequestURI().endsWith("mabout")) return true;
		else if(request.getRequestURI().endsWith("emailAuthCheck")) return true;
		else if(request.getRequestURI().endsWith("smscheck")) return true;
		else if(request.getRequestURI().endsWith("kakaologin")) return true;
		else if(request.getRequestURI().endsWith("kakaologout")) return true;
		else if(request.getRequestURI().endsWith("kakaoout")) return true;
		else if(request.getRequestURI().endsWith("kakaosignup")) return true;
		else if(request.getRequestURI().endsWith("test")) return true;
		else if(request.getRequestURI().endsWith("map")) return true;
		else if(request.getRequestURI().endsWith("footer")) return true;
		else if(request.getRequestURI().endsWith("header")) return true;
		else if(request.getRequestURI().endsWith("wboard")) return true;
		else if(request.getRequestURI().endsWith("websocket")) return true;
		else if(request.getRequestURI().endsWith("summerwrite")) return true;
		else if(request.getRequestURI().endsWith("wDelete")) return true;
		else if(request.getRequestURI().endsWith("summerupdate")) return true;
		else if(request.getRequestURI().endsWith("summerUpdateres")) return true;
		else if(request.getRequestURI().endsWith("wbAllList")) return true;
		else if(request.getRequestURI().endsWith("wbMyList")) return true;
		else if(request.getRequestURI().endsWith("wbdatesend")) return true;
		else if(request.getRequestURI().endsWith("footer")) return true;
		else if(request.getRequestURI().endsWith("header")) return true;
		else if(request.getRequestURI().endsWith("wboard")) return true;
		else if(request.getRequestURI().endsWith("wSelectOne")) return true;
		
		else if(request.getRequestURI().endsWith("pagechange")) return true;
		else if(request.getRequestURI().endsWith("crwl")) return true;
		
		else if(request.getRequestURI().endsWith("map")) return true;
		else if(request.getRequestURI().endsWith("search")) return true;
		else if(request.getRequestURI().endsWith("marker")) return true;
		else if(request.getRequestURI().endsWith("delete")) return true;
		else if(request.getRequestURI().endsWith("update")) return true;
		else if(request.getRequestURI().endsWith("editor")) return true;
		
		else if(request.getRequestURI().endsWith("null")) return false;
		
		else {
			
			if(user == null) response.sendRedirect("/dashboard/login");
			else return true;
			
		}
		
		return false;
	}
	
	
}
