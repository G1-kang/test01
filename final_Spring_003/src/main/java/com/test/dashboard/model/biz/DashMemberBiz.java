package com.test.dashboard.model.biz;

import java.sql.SQLException;
import java.util.List;

import com.test.dashboard.model.dto.DashMemberDto;

public interface DashMemberBiz {

	// 대쉬보드 맴버 조회
	public List<DashMemberDto> selectList(int dno);
	
	// 맴버테이블 NO로 한명 조회
	public DashMemberDto selectByNo(int dmno);

	// 맴버테이벌 대쉬보드 NO와 맴버 ID로 조회
	public DashMemberDto selectById(int dno, String mid);
	
	public int insert(List<DashMemberDto> params , int dno) throws SQLException;
	
	public int update(DashMemberDto dto);
	
	public int delete(int dmno);
	
}
