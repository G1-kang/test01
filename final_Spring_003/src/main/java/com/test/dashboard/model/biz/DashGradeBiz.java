package com.test.dashboard.model.biz;

import java.util.List;
import java.util.Map;

import com.test.dashboard.model.dto.DashGradeDto;

public interface DashGradeBiz {

	// 특정 대쉬보드의 등급 목록
	public List<DashGradeDto> selectList(int dno);
	
	public DashGradeDto selectOne(Map<String, Object> params);
	
	public int insert(DashGradeDto dto);
	
	public int update(DashGradeDto dto);
	
	public int delete(int dgno);
}
