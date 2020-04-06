package com.test.dashboard.model.biz;

import java.util.List;

import com.test.dashboard.model.dto.WMemoDto;

public interface WMemoBiz {
	public List<WMemoDto> selectList();
	
	public WMemoDto selectOne(int wno);
	
	public int insert(WMemoDto wMemoDto);
	
	public int update(WMemoDto wMemoDto);
	
	public int delete(int wno);
	
}
