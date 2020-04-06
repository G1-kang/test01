package com.test.dashboard.controller;

import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.test.dashboard.model.biz.MemberBiz;
import com.test.dashboard.model.dto.DashAddObjectDto;
import com.test.dashboard.model.dto.DashMemberDto;
import com.test.dashboard.model.dto.WChatDto;
import com.test.dashboard.model.dto.WidgetDto;

@Controller
public class WebSocketController {

	private Logger logger = LoggerFactory.getLogger(WebSocketController.class);
	
	@Autowired
	private MemberBiz memberBiz;
	
	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/wchat")
	public void wChatMessage(WChatDto wChatDto) {

		logger.info("[ INFO ] : WebSocketController > wChatMessage [wChatDto : " + wChatDto + "]");

		template.convertAndSend("/sub/wchat/" + wChatDto.getWno(), wChatDto);
	
	}
	
	@MessageMapping("/wmemo")
	public void wMemoMessage(Map<String, Object> params) {

		logger.info("[ INFO ] : WebSocketController > wMemoMessage [params : " + params + "]");

		template.convertAndSend("/sub/wmemo/" + params.get("wno"), params);
	
	}

	@MessageMapping("/wscale")
	public void wScaleMessage(WidgetDto widgetDto) {

		logger.info("[ INFO ] : WebSocketController > wScaleMessage [widgetDto : " + widgetDto + "]");

		template.convertAndSend("/sub/wscale/" + widgetDto.getWno(), widgetDto);
	
	}
	
	@MessageMapping("/wloc")
	public void wLocMessage(WidgetDto widgetDto) {

		logger.info("[ INFO ] : WebSocketController > wLocMessage [widgetDto : " + widgetDto + "]");

		template.convertAndSend("/sub/wloc/" + widgetDto.getWno(), widgetDto);
	
	}
	
	@MessageMapping("/wdel")
	public void wDelMessage(WidgetDto widgetDto) {

		logger.info("[ INFO ] : WebSocketController > wDelMessage [wno : " + widgetDto.getWno() + "]");

		template.convertAndSend("/sub/wdel/" + widgetDto.getWno(), widgetDto);
	
	}
	
	@MessageMapping("/wup")
	public void wUpMessage(WidgetDto widgetDto) {

		logger.info("[ INFO ] : WebSocketController > wUpMessage [WidgetDto : " + widgetDto + "]");

		template.convertAndSend("/sub/wup/" + widgetDto.getWno(), widgetDto);
	
	}
	
	@MessageMapping("/wadd")
	public void wAddMessage(WidgetDto widgetDto) {

		logger.info("[ INFO ] : WebSocketController > wAddMessage [WidgetDto : " + widgetDto + "]");
		
		template.convertAndSend("/sub/wadd/" + widgetDto.getDno(), widgetDto);
		
	}
	
	@MessageMapping("/addDash")
	public void dAddMessage(DashMemberDto dashMemberDto) {
			
		int mno = memberBiz.selectById(dashMemberDto.getMid()).getMno();
			
		logger.info("[ INFO ] : WebSocketController > dAddMessage [mno : " + mno + "]");
			
		template.convertAndSend("/sub/addDash/" + mno, dashMemberDto);
			
		
	}
	
	@MessageMapping("/delDash")
	public void dDelMessage(DashMemberDto dashMemberDto) {
			
		int mno = memberBiz.selectById(dashMemberDto.getMid()).getMno();
			
		logger.info("[ INFO ] : WebSocketController > dDelMessage [mno : " + mno + "]");
			
		template.convertAndSend("/sub/delDash/" + mno, dashMemberDto);
			
		
	}
	
	
	@MessageMapping("/wcrkeyword")
	public void wCrKeywordMessage(Map<String, Object> params) {
			
		logger.info("[ INFO ] : WebSocketController > wCrKeywordMessage [keyword : " + params.get("keyword") + "]");
			
		template.convertAndSend("/sub/wcrkeyword/" + params.get("wno"), params);
			
		
	}
	
}
