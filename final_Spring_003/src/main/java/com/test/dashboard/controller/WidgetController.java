package com.test.dashboard.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dashboard.common.util.Util;
import com.test.dashboard.model.biz.WChatBiz;
import com.test.dashboard.model.biz.WMemoBiz;
import com.test.dashboard.model.biz.WidgetBiz;
import com.test.dashboard.model.dto.MemberDto;
import com.test.dashboard.model.dto.WChatDto;
import com.test.dashboard.model.dto.WMemoDto;
import com.test.dashboard.model.dto.WRuleDto;
import com.test.dashboard.model.dto.WidgetDto;


@RestController
@RequestMapping("/widget")
public class WidgetController {

	private Logger logger = LoggerFactory.getLogger(WidgetController.class);
	
	@Autowired
	private WidgetBiz widgetBiz;
	
	@Autowired
	private WMemoBiz wmemoBiz;
	
	@Autowired
	private WChatBiz wChatBiz; 
	
	@GetMapping("/list")
	public List<WidgetDto> getListWidget(HttpSession session){
		
		int dno = (int) session.getAttribute("selectBoard");
		MemberDto owner = ((MemberDto)session.getAttribute("user"));
		
		return widgetBiz.selectList(dno, owner.getMid());
		
	}
	
	@GetMapping("/{wno}")
	public WidgetDto getWidget(@PathVariable int wno) {
		
		logger.info("[ INFO ] : WidgetController > getWidget [path : /widget/"+ wno +"]");
		
		return widgetBiz.selectOne(wno);
	}
	
	@PostMapping("/update")
	public WidgetDto postUpdateWidget(@RequestBody WidgetDto widgetDto, HttpSession session) {
		
		
		logger.info("[ INFO ] : WidgetController > postUpdateWidget [path : /widget/update]");
		
		int res = 0;
		
		MemberDto owner = ((MemberDto)session.getAttribute("user"));
		
		if(widgetDto.getMid().equals(owner.getMid())){
			
			WRuleDto ownerRule = new WRuleDto();
			
			ownerRule.setMid(owner.getMid());
			ownerRule.setMnick(owner.getMnick());
			ownerRule.setWrcategory("individual");
			ownerRule.setWrrwd(6);
			
			widgetDto.getRules().add(ownerRule);
			
			logger.info("[ INFO ] : WidgetController > postUpdateWidget [widgetDto : " + widgetDto +"]");
			
			res = widgetBiz.update(widgetDto);
		}
		
		
		if(res > 0) {
			return widgetDto;
		} else {
			return null;
		}
	}
	
	@PostMapping("/insert")
	public WidgetDto postInsertWidget(@RequestBody WidgetDto widgetDto, HttpSession session) {
		
		logger.info("[ INFO ] : WidgetController > postInsertWidget [path : /widget/insert]");
		
		int dno = (int) session.getAttribute("selectBoard");
		MemberDto owner = ((MemberDto)session.getAttribute("user"));
		
		widgetDto.setDno(dno);
		widgetDto.setMid(owner.getMid());
		
		WRuleDto ownerRule = new WRuleDto();
		
		ownerRule.setMid(owner.getMid());
		ownerRule.setMnick(owner.getMnick());
		ownerRule.setWrcategory("individual");
		ownerRule.setWrrwd(6);
		
		widgetDto.getRules().add(ownerRule);
		
		logger.info("[ INFO ] : WidgetController > postInsertWidget [widgetDto : " + widgetDto +"]");
		
		int res = widgetBiz.insert(widgetDto);
		
		if(res > 0) {
			return widgetDto;
		} else {
			return null;
		}
		
	}
	
	@GetMapping("/wmemo/{wno}")
	public WMemoDto getWMemo(@PathVariable int wno) {
		logger.info("[ INFO ] : WidgetController > getWMemo [wno : " + wno +"]");
		
		WMemoDto wMemoDto = wmemoBiz.selectOne(wno);
		
		if(wMemoDto != null) {
			return wMemoDto;
		} else {
			return null;
		}
		
	}
	
	@PostMapping("/wmemo")
	public WMemoDto postInsertWMemo(@RequestBody WMemoDto wMemoDto, HttpServletRequest req, HttpSession session) {
		
		WMemoDto check = wmemoBiz.selectOne(wMemoDto.getWno());
		
		String wmcontent = wMemoDto.getWmcontent();
		
		Document doc = Jsoup.parse(wmcontent);
		
		Elements els = doc.getElementsByTag("img");
		
		Iterator<Element> li = els.iterator();
		
		while(li.hasNext()) {
			
			Element el = li.next();
			
			String chk = el.className();
			
			if(!chk.equals("pC")) {
				String img = el.attr("src");
			
				String filePath = Util.base64ToImgDecoder(img, req.getServletContext().getRealPath("/"), ((MemberDto)session.getAttribute("user")).getMid() ,req.getServletContext().getContextPath());
			
				el.attr("src", filePath);
			
				el.addClass("pC");
			}
			
		}
		
		wMemoDto.setWmcontent(doc.toString());;
		
		int res;
		if(check == null) {
			res = wmemoBiz.insert(wMemoDto);
		} else {
			res = wmemoBiz.update(wMemoDto);
		}
		
		logger.info("[ INFO ] : WidgetController > postInsertWMemo [wMemoDto : " + wMemoDto +"]");
		
		if(res > 0) {
			return wMemoDto;
		} else {
			return null;
		}
		
	}
	
	@GetMapping("/wchat/{wno}")
	public WChatDto getWChat(@PathVariable int wno) {
		
		logger.info("[ INFO ] : WidgetController > getWChat [wno : " + wno +"]");
		WChatDto wChatDto = wChatBiz.selectOne(wno);
		
		if(wChatDto != null) {
			return wChatDto;
		} else {
			return null;
		}
	}
	
	
	@PostMapping("/wchat")
	public WChatDto postInsertWChat(@RequestBody WChatDto wChatDto, HttpServletRequest req, HttpSession session) {
		
		int res = 0;
		
		int wno = wChatDto.getWno();
		
		
		String savePath = req.getServletContext().getRealPath("/");
		String viewPath = req.getServletContext().getContextPath();
		
		if(wChatDto.getWcpath() == null || wChatDto.getWcpath().equals("")) {
				
			wChatDto.setWcpath(Util.chatLogFile(wno, savePath, viewPath));
			res = wChatBiz.insert(wChatDto);
				
		} else {
			
			String message = wChatDto.getMsg();
			String mnick = wChatDto.getMnick();
			
			Document doc = Jsoup.parse(message);
			
			Elements els = doc.getElementsByTag("img");
			
			Iterator<Element> li = els.iterator();
			
			while(li.hasNext()) {
				
				Element el = li.next();
			
				String chk = el.className();
			
				if(!chk.equals("pC")) {
					String img = el.attr("src");
				
					String filePath = Util.base64ToImgDecoder(img, req.getServletContext().getRealPath("/"), ((MemberDto)session.getAttribute("user")).getMid() ,req.getServletContext().getContextPath());
				
					el.attr("src", filePath);
			
					el.addClass("pC");
				}
			
			}
			
			message = doc.toString().replaceAll("(\r\n|\r|\n|\n\r)", " ");
			Util.chatLogFile(wno, savePath, viewPath, wChatDto.getWcpath() ,mnick, message);
		
			res = 1;
		
		}
			
		
		logger.info("[ INFO ] : WidgetController > postInsertWChat [wChatDto : " + wChatDto +"]");
		
		if(res > 0) {
			return wChatDto;
		} else {
			return  null;	
		}
	
	}
	
	
	@GetMapping("/updatewdel/{wno}")
	public boolean getUpdateWdel(@PathVariable int wno) {
		logger.info("[ INFO ] : WidgetController > getUpdateWdel [wno : " + wno +"]");
		int res = widgetBiz.updateWdel(wno);
		
		if(res > 0) {
			logger.info("[ INFO ] : WidgetController > getUpdateWdel [success]");
			return true;
		} else {
			return false;
		}
	}
	
	@PostMapping("/topleftupdate")
	public int postTopLeftUpdate(@RequestBody WidgetDto widgetDto) {
//		logger.info("[ INFO ] : WidgetController > postTopLeftUpdate [widgetDto : " + widgetDto +"]");
		return widgetBiz.topLeftUpdate(widgetDto);
		
	}
	
	@PostMapping("/widthHeightUpdate")
	public int postWidthHeightUpdate(@RequestBody WidgetDto widgetDto) {
		logger.info("[ INFO ] : WidgetController > postWidthHeightUpdate [widgetDto : " + widgetDto +"]");
		return widgetBiz.widthHeightUpdate(widgetDto);
	}
	
	
}
