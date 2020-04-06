package com.test.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.dashboard.model.biz.WboardBiz;
import com.test.dashboard.model.dto.WboardDto;

@Controller
public class WboardController {

		private Logger logger = LoggerFactory.getLogger(WboardController.class);
		
		@Autowired
		private WboardBiz biz;
		
		//성공시 삭제할 것 - 필요없음.. 페이지 전환 없음 
		@RequestMapping("/wboard")
		//나중에 int dgno를 추가해서  해당대시 보드에 들어가는 list를 뽑아내자 
		public String postWboard(Model model) {
			logger.info("[ INFO ] : wboardController > wlist");
			
			//System.out.println(dgno);
			
			model.addAttribute("list",biz.boardList());
			//model.addAttribute("list",biz.boardListAll(dgno));
			
			return "wboard";
		}
		
		//전체 글 목록 보기 
		@RequestMapping(value ="/wbAllList", method=RequestMethod.POST)
		@ResponseBody
		public List<WboardDto> boardListAll(@ModelAttribute("wno") int wno){
			
			List<WboardDto> list = biz.boardListAll(wno);

			return list;
			
		}
		
		//내글목록 보기 
		@RequestMapping(value= "/wbMyList", method = RequestMethod.POST)
		@ResponseBody
		public List<WboardDto> boardMyList(WboardDto dto){
			
			
			//System.out.println(mid+"와"+ wno+"는 여기 내글목록 보기에서 송출된 것" );
			List<WboardDto> list = biz.boardMyList(dto);
			
			System.out.println(list);
			
			return list;
		}
		
		
		@RequestMapping(value = "/wSelectOne", method = RequestMethod.POST)
		@ResponseBody
		//post방식으로 데이터를 주고 받을 경우 @ModelAttribute("받아오는 값 이름") 디비에서 이용되는 값이름  ,를 사용해서 이름을 맞춰주어야 에러가 안뜬다  
		public Map<String, Object> selectOne(@ModelAttribute("selectno") int wbtodono) {
			//System.out.println("으아아앙ㄱ " + wbtodono);
			
			//글 상세보기 
			//wwdto.getWbtodono()
			
			WboardDto dto = biz.wSelectOne(wbtodono);
			//System.out.println("디티오 ㅂ부ㅜㄴ"+dto.getMid()+dto.getWbtitle()+dto.getWbstartdate());
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("wbtodono",dto.getWbtodono());
			map.put("wno",dto.getWno());
			map.put("dno",dto.getDno());
			map.put("dgno",dto.getDgno());
			map.put("mid",dto.getMid());
			map.put("wbtodo",dto.getWbtodo());
			map.put("wbtitle",dto.getWbtitle());
			map.put("wbcontent",dto.getWbcontent());
			map.put("wfno_list", dto.getWfno_list());
			map.put("wbstartdate", dto.getWbstartdate());
			map.put("wbenddate", dto.getWbenddate());
			map.put("wbcolor", dto.getWbcolor());
		    

			return map;
		
		}
		
		
		@RequestMapping(value="/wDelete", method= RequestMethod.POST)
		@ResponseBody
		public boolean wDelete(@ModelAttribute("selectno") int wbtodono) {
			
			int res = biz.wDelete(wbtodono);
			
			if(res > 0) {
				return true;
			}else {
				return false;
			}
			
		}
		
		
		/*
		@GetMapping("/summerwrite")
		public String boardWrite() {
		  return "summerboardwrite";
		}
		*/
		
		//@PostMapping("/summerwrite")
		@RequestMapping(value = "/summerwrite",method = RequestMethod.POST)
		@ResponseBody
		public boolean wbinsert(WboardDto dto) {
			//System.out.println(dto.getWbstartdate()+"그리고"+dto.getWbenddate());
			
			int	res = biz.wbinsert(dto);
			if(res > 0) {
				System.out.println("저장완료");
				
				return true;
				
			}else {
				System.out.println("저장안됨");
				return false;
			}
			
		
		}
		

		
		
		//게시글 수정 폼으로 보내기 
		@RequestMapping(value="/summerupdate", method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> summerUpdate(@ModelAttribute("selectno") int wbtodono){
			
			//System.out.println("여기는 업데이트"+wbtodono);
			WboardDto dto = biz.wSelectOne(wbtodono);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("wbtodono",dto.getWbtodono());
			map.put("wno",dto.getWno());
			map.put("dno",dto.getDno());
			map.put("dgno",dto.getDgno());
			map.put("mid",dto.getMid());
			map.put("wbtodo",dto.getWbtodo());
			map.put("wbtitle",dto.getWbtitle());
			map.put("wbcontent",dto.getWbcontent());
			map.put("wfno_list", dto.getWfno_list());
			map.put("wbstartdate", dto.getWbstartdate());
			map.put("wbenddate", dto.getWbenddate());
			map.put("wbcolor", dto.getWbcolor());
			
			
			return map;
		}
		
		
		
		//게시글 수정 완료 해서 저장하기 
		@RequestMapping(value = "/summerUpdateres", method = RequestMethod.POST)
		@ResponseBody
		public boolean summerUpdate( WboardDto dto) {
			//@RequestBody를 붙여주면 컨트롤러로 전송된 JSON 정보가 자동으로 Map으로 변환되어 해당 변수에 저장됩니다. (변수명은 아무렇게나 지어도 상관없습니다.)
			//josn형태로 변환하는 contentType : 'application/json'를 쓰지 않을 때는 @requestbody를 적으면 안됨 파써에러남! 
			
			
			//System.out.println(dto.getWbtodono()+"수정의 내용 :"+dto.getWbstartdate()+dto.getWbenddate());
			int res = biz.summerUpdate(dto);
			//System.out.println("수정에서  결과값 "+res);
			if(res>0) {
				return true;
			}else {
				return false;	
			}
			
		}
		
		//달력 날짜 보내기 
		@RequestMapping(value = "/wbdatesend", method = RequestMethod.POST)
		@ResponseBody
		public List<WboardDto> wbdatesend(WboardDto dto){
			
			//String mid, int yyyymm 형태의 wbstartdate, int wno 3가지의 값이 일치하는 것 의 달력 ㄱㄱ 
			List<WboardDto> list = biz.wbdatesend(dto);
			System.err.println("어디까지 들어오는지 보기 위해서 여기느 달력 날짜 보내기 "+dto.getWno()+dto.getMid()+dto.getWbstartdate());
			System.out.println(list);
			return list;
		}
		

		
}
