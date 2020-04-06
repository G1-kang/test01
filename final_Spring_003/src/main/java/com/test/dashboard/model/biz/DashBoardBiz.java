package com.test.dashboard.model.biz;

import java.util.List;

import com.test.dashboard.model.dto.DashBoardDto;

public interface DashBoardBiz {

	//본인 소유 보드 조회
	public List<DashBoardDto> selectByOwner(String mid);
	
	//본인 소속 보드 조회
	public List<DashBoardDto> selectByBelong(String mid);

	//보드 입장
	public DashBoardDto selectOne(int dno);
	
	public int insert(DashBoardDto dto);
	
	public int update(DashBoardDto dto);
	
	public int updateDel(int dno);
	
	public int delete(int dno);
}
