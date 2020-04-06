package com.test.dashboard.model.biz;

import java.util.List;

import com.test.dashboard.model.dto.WMapDto;

public interface WMapBiz {
	
	public List<WMapDto> selectList(int wno);
	//public List<WMapDto> selectList();
	public WMapDto select(int wmapno); 
	public int insert(WMapDto dto);
	public int update(WMapDto dto);
	public int delete(int wmapno);

}
