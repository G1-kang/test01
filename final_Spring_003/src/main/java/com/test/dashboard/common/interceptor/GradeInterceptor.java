package com.test.dashboard.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.test.dashboard.model.biz.DashMemberBiz;
import com.test.dashboard.model.biz.WRuleBiz;
import com.test.dashboard.model.dto.DashMemberDto;
import com.test.dashboard.model.dto.MemberDto;
import com.test.dashboard.model.dto.WRuleDto;


public class GradeInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(GradeInterceptor.class);
	
	@Autowired
	private WRuleBiz wRuleBiz;
	
	@Autowired
	private DashMemberBiz dashMemberBiz;
	
	@SuppressWarnings("null")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		MemberDto memberDto = (MemberDto) session.getAttribute("user");
		
		int dno = (int) session.getAttribute("selectBoard");
		
		if(request.getMethod().equals("POST")) {
			
			logger.info("[ Interceptor ] Permission Check");
			
			if(request.getAttribute("requestBody") != null) {
				
				String requestBody = ((String) request.getAttribute("requestBody"));
				Gson gs = new Gson();
				JsonElement je = gs.fromJson(requestBody, JsonElement.class);
				JsonObject jo = je.getAsJsonObject();
				
				if(jo.get("wno") != null) {
					int wno = jo.get("wno").getAsInt();

					DashMemberDto dashMemberDto = dashMemberBiz.selectById(dno, memberDto.getMid());

					List<WRuleDto> rules = wRuleBiz.selectList(wno);

					for(WRuleDto rule : rules) {
						if(rule.getWrcategory().equals("individual")) {
							if (rule.getMid().equals(memberDto.getMid())) {
								if(rule.getWrrwd() == 6) {
									logger.info("[ Interceptor ] Permission Access");
									return true;
								} else {
									logger.info("[ Interceptor ] Permission fail");
									response.sendRedirect("/dashboard/notpermission");
								}
							}
						} else if (rule.getWrcategory().equals("group")) {
							int dggrade = dashMemberDto.getDggrade();
							if(rule.getWrmin() <= dggrade && dggrade <= rule.getWrmax()) {
								if(rule.getWrrwd() == 6) {
									logger.info("[ Interceptor ] Permission Access");
									return true;
								} else {
									logger.info("[ Interceptor ] Permission fail");
									response.sendRedirect("/dashboard/notpermission");
								}
							}

						}
					}

				}
			}
		}
		
		return true;
		
	}

}
