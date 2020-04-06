package com.test.dashboard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.dashboard.common.crawling.crawling;
import com.test.dashboard.model.dto.CrawlingDto;

@Controller
public class CrawlingController {

	private Logger logger = LoggerFactory.getLogger(CrawlingController.class);
	
	@ResponseBody
	@RequestMapping("/crwl")
	public List<CrawlingDto> crwl (@RequestBody @RequestParam(value="keyword", required = false, defaultValue = "null") String keyword) {
	
		logger.info("========================================>>>>>>>>"+keyword);
		crawling crwl = new crawling();
		if (keyword != null) {
			return crwl.crwlparser(keyword); 
		}
		return crwl.crwlparser(keyword);		
	}

	@RequestMapping("/pagechange")
	public String pagechange() {
		return "crawling";
	}
	
}
