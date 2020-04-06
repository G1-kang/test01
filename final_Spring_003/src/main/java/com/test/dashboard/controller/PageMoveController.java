package com.test.dashboard.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class PageMoveController {

	private Logger logger = LoggerFactory.getLogger(PageMoveController.class);
	
	
	@GetMapping("board")
	public String getDashboard(){
		
		logger.info("[ INFO ] : MainController > getDashboard [path : /board]");
		
		return "board";

	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("board/{dno}")
	public String getDashboardDno(@PathVariable int dno, HttpSession session){
		
		logger.info("[ INFO ] : MainController > getDashboard [path : /board/" + dno + "]");
		
		Set<Integer> selectBoardList = new HashSet<Integer>();
		
		if(session.getAttribute("selectBoardList") != null) {
			selectBoardList.addAll((Set<Integer>)session.getAttribute("selectBoardList"));
		}
		
		selectBoardList.add(dno);
		
		session.setAttribute("selectBoard", dno);
		session.setAttribute("selectBoardList", selectBoardList);
		
		return "redirect:/board";

	}
	
	@GetMapping("mypage")
	public String getMyPage(Model model, HttpSession session) {
		logger.info("[ INFO ] : MainController > getMyPage [path : /mypage/]");
		return "mypage";
	}
	
	@GetMapping("login")
	public String getLogin() {
		logger.info("[ INFO ] : MainController > getLogin [path : /login]");
		return "login";
	}
	
	@GetMapping("header")
	public String getHeader() {
		logger.info("[ INFO ] : MainController > getLogin [path : /login]");
		return "header";
	}
	
	@GetMapping("footer")
	public String getFooter() {
		logger.info("[ INFO ] : MainController > getLogin [path : /login]");
		return "footer";
	}
	
	@GetMapping("test")
	public String getTest() {
		logger.info("[ INFO ] : MainController > getLogin [path : /login]");
		return "test";
	}
}
