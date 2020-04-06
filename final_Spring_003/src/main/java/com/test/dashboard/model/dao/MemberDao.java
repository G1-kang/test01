package com.test.dashboard.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.test.dashboard.model.dto.MemberDto;

@Repository
public interface MemberDao {

	// 맴버 리스트
	@Select("select * from member where mdel='N'")
	public List<MemberDto> selectList();

	// 번호 조회
	@Select("select * from member where mdel='N' and mno = #{mno}")
	public MemberDto selectByMNo(int mno);

	// id 조회
	@Select("select * from member where mid = #{mid}")
	public MemberDto selectById(String mid);

	// nick 조회
	@Select("select mid, mnick, mimgpath from member where mnick = #{mnick}")
	public MemberDto selectByNick(String mnick);

	// 이름 조회
	@Select("select mid from member where mname = #{mname} and mplatform = 'home'")
	public int selectByName(String mname);

	// 아이디 찾기
	@Select("select mid from member where mname = #{mname} and memail = #{memail} and mplatform is null")
	public MemberDto idSearchByName(MemberDto memberDto);

	// 비밀번호 재설정 유효성
	@Select("select mno from member where mid = #{mid} and memail = #{memail} and mphone = #{mphone} and mplatform is null")
	public MemberDto pwSearchCheck(MemberDto memberDto);

	// 비밀번호 아이디
	@Select("select * from member where mid = #{mid} and mpw = #{mpw}")
	public MemberDto selectByIdAndPw(Map<String, Object> params);

	// mabout 입력
	@Update("update member set mabout=#{mabout} where mno=#{mno}")
	public int maboutUpdate(MemberDto memberDto);

	@Update("update member set mid=#{mid}, mname=#{mname}, mnick=#{mnick}, mpw=#{mpw}, memail=#{memail}, maddr=#{maddr}, mphone=#{mphone}, mabout=#{mabout}, mimgpath = #{mimgpath}, mgrade = #{mgrade}, mdel=#{mdel} where mno = #{mno}")
	public int update(MemberDto memberDto);
	
	@Update("update member set mpw=#{mpw} where mno = #{mno}")
	public int passwordUpdate(MemberDto memberDto);
	
	@Delete("delete from member where mno = #{mno}")
	public int delete(int mno);

	@SelectKey(statement = "select member_seq.nextval from dual", before = true, keyProperty = "mno", resultType = Integer.class)
	@Insert("insert into member values(#{mno}, #{mid}, #{mnick}, #{mname}, #{mpw}, #{memail}, #{maddr}, #{mphone}, #{mabout}, #{mimgpath}, #{mgrade}, sysdate, 'N', #{mplatform})")
	public int insert(MemberDto memberDto);

}
