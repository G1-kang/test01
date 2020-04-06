package com.test.dashboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.dashboard.model.dto.WEditorDto;

@Controller
public class EditorController {
	private Logger logger = LoggerFactory.getLogger(EditorController.class);
	
	
	@GetMapping("editor")
	public String getEditor() {
		logger.info("editor 오픈");
		
		return "editor";
	}
	
	@PostMapping("run")
	@ResponseBody
	public String runEditor(@RequestBody WEditorDto dto) {
		
		
		
		
		return "";
	}

}
