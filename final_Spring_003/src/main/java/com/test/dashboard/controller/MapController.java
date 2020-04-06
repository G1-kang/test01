package com.test.dashboard.controller;


import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.dashboard.model.biz.WMapBiz;
import com.test.dashboard.model.dto.WMapDto;

import edu.emory.mathcs.backport.java.util.Arrays;

@Controller
public class MapController {

	private Logger logger = LoggerFactory.getLogger(MapController.class);

	@Autowired
	private WMapBiz wMapBiz;

	@GetMapping("map")
	public String getMap() {
		logger.info("[ INFO ] : MapController");
		
		//session.setAttribute("user", new MemberDto());
		
		return "map";
	}
	
	@GetMapping("search")
	public String getSearch() {
		logger.info("[ INFO ] : 검색 들어옴");
		return "map";
	}

	@PostMapping("map")
	@ResponseBody
	public boolean addLocation(@RequestBody WMapDto wMapDto) {

		List<WMapDto> list = wMapBiz.selectList(1);
		wMapDto.setWno(1);
		
		boolean boolRes = false;
		
		Boolean[] exist = new Boolean[list.size()];
//		logger.info("배열 생성 사이즈는 : " + exist.length);
		for(int i = 0; i < list.size(); i++) {
			
			
			if((wMapDto.getWno() == list.get(i).getWno() && wMapDto.getWmapkeyword().equals(list.get(i).getWmapkeyword()) && wMapDto.getWmaplat().equals(list.get(i).getWmaplat()) && wMapDto.getWmaplng().equals(list.get(i).getWmaplng()))) {
				exist[i] = true;
			} else {
				exist[i] = false;
			}
		}
		
		logger.info("배열 : " + Arrays.toString(exist));
		
		if(!(Arrays.asList(exist).contains(true))) {
			int res = wMapBiz.insert(wMapDto);
			if(res > 0) {
				boolRes = true;
			}
		}
		return boolRes;
	}
	
	@PostMapping("update")
	@ResponseBody
	public boolean updateMemo(WMapDto wMapDto) {
		
		logger.info(wMapDto.getWmapmemo());
		
		int res = wMapBiz.update(wMapDto);
		
		if(res > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	@PostMapping("delete")
	@ResponseBody
	public boolean deleteLocation(@RequestBody Map<Object, Integer> map) {
		
		int wmapno = map.get("temp");
		
		int res = wMapBiz.delete(wmapno);
		
		if(res > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	@PostMapping("marker")
	@ResponseBody
	public List<WMapDto> postMarker() {

		List<WMapDto> list = wMapBiz.selectList(1);
		
		System.out.println(list);
		
		return list;
	}
	

}
