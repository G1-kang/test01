package com.test.dashboard.model.biz;

import java.util.List;

import com.test.dashboard.model.dto.MemberDto;

public interface MemberBiz {

	// 맴버 리스트
	public List<MemberDto> selectList();
	
	// 번호 조회
	public MemberDto selectByMNo(int mno);
	
	// 비밀번호 아이디
	public MemberDto selectByIdAndPw(String mid, String mpw);
	
	// 이이디 조회
	public MemberDto selectById(String mid);
	
	// 닉네임 조회
	public MemberDto selectByNick(String mnick);
	
	// 아이디 찾기
	public MemberDto idSearchByName(MemberDto memberDto);
	
	public MemberDto pwSearchCheck(MemberDto memberDto);
	
	// 이름 조회
	public int selectByName(String mname);
	
	public int insert(MemberDto memberDto);
	
	public int maboutUpdate(MemberDto memberDto);
	
	public int passwordUpdate(MemberDto memberDto);
	
	public int update(MemberDto memberDto);
	
	public int delete(int mno);
	
}
