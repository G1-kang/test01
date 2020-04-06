package com.test.dashboard.model.biz;

import java.util.List;

import com.test.dashboard.model.dto.WChatDto;

public interface WChatBiz {

	public WChatDto selectOne(int wno);

	public List<WChatDto> selectList(int dno);
	
	public int insert(WChatDto wChatDto);
	
	public int delete(int wno);
	
}
