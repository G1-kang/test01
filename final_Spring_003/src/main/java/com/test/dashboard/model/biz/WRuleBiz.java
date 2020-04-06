package com.test.dashboard.model.biz;

import java.util.List;

import com.test.dashboard.model.dto.WRuleDto;

public interface WRuleBiz {

	List<WRuleDto> selectList(int wno);
	
	WRuleDto selectOne(int wrno);
	
	int insert(WRuleDto wRuleDto);
	
	int update(WRuleDto wRuleDto);
	
	int delete(int wrno);
	
	int wnoDelete(int wno);
}
