package com.test.dashboard.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dashboard.model.biz.DashBoardBiz;
import com.test.dashboard.model.biz.DashGradeBiz;
import com.test.dashboard.model.biz.DashMemberBiz;
import com.test.dashboard.model.dto.DashBoardDto;
import com.test.dashboard.model.dto.DashGradeDto;
import com.test.dashboard.model.dto.DashMemberDto;


@RequestMapping("/board")
@RestController
public class DashBoardController {

	
	@Autowired
	private DashBoardBiz dashBoardBiz;
	
	@Autowired
	private DashMemberBiz dashMemberBiz;
	
	@Autowired
	private DashGradeBiz dashGradeBiz;
	
	private Logger logger = LoggerFactory.getLogger(DashBoardController.class);

	@GetMapping("/select/{dno}")
	public DashBoardDto getSelectDashBoardNo(@PathVariable int dno, HttpSession session) {
		
		session.setAttribute("selectBoard", dno);
		
		logger.info("[ INFO ] : DashBoardController > getSelectDashBoardNo [path : /board/select/"+ dno +"]");
		
		return dashBoardBiz.selectOne(dno);
	
	}
	
	@GetMapping("/select")
	public DashBoardDto getSelectDashBoard(HttpSession session) {
		
		int dno = (int) session.getAttribute("selectBoard");
		
		logger.info("[ INFO ] : DashBoardController > getSelectDashBoard [path : /board/select/"+ dno +"]");
		
		return dashBoardBiz.selectOne(dno);
	
	}
	
	@GetMapping("/rules")
	public List<DashGradeDto> getRules(HttpSession session){
		
		logger.info("[ INFO ] : DashBoardController > getRules [path : /board/rules]");
		int dno = (int) session.getAttribute("selectBoard");
		return dashGradeBiz.selectList(dno);
		
	}
	
	@GetMapping("/members")
	public List<DashMemberDto> getMembers(HttpSession session){
		
		logger.info("[ INFO ] : DashBoardController > getMembers [path : /board/members]");
		int dno = (int) session.getAttribute("selectBoard");
		return dashMemberBiz.selectList(dno);
		
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/selectList")
	public List<DashBoardDto> getSelectListDashBoard(HttpSession session){
		
		logger.info("[ INFO ] : DashBoardController > getSelectListDashBoard [path : /board/selectList]");
		
		List<DashBoardDto> selectList = new ArrayList<DashBoardDto>();
		
		Set<Integer> selectBoardList = ((Set<Integer>)session.getAttribute("selectBoardList"));
		
		Iterator<Integer> iterator = selectBoardList.iterator();
		
		while(iterator.hasNext()) {
			
			selectList.add(dashBoardBiz.selectOne(iterator.next()));
			
		}
		
		return selectList;
		
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/dashboardClose")
	public int getDashboardClose(DashBoardDto dashBoardDto, HttpSession session) {
		
		logger.info("[ INFO ] : DashBoardController > getDashboardClose [path : /board/dashboardClose]");
		
		Set<Integer> selectBoardList = ((Set<Integer>)session.getAttribute("selectBoardList"));
		boolean res = selectBoardList.remove(dashBoardDto.getDno());
		
		if(res) {
			session.setAttribute("selectBoardList", selectBoardList);
			if(dashBoardDto.getDno() == (int)session.getAttribute("selectBoard")) {
				Iterator<Integer> iterator = selectBoardList.iterator();
				if(iterator.hasNext()) {
					int dno = iterator.next();
					System.out.println(dno);
					session.setAttribute("selectBoard", dno);
					return dno;
				} else {
					return 0;
				}
			} else {
				return dashBoardDto.getDno();
			}
		} else {
			return -1;
		}
	}
	
	
}
