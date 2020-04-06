package com.test.dashboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.test.dashboard.model.dto.WFileDto;

@Repository
public interface WFileDao {

	// 위젯 번호로 찾기
	@Select("select * from wfile where wno = #{wno}")
	public List<WFileDto> selectByWNo(int wno);
	
	// 파일번호로 하나 찾기
	@Select("select * from wfile where wfno = #{wfno}")
	public WFileDto selectOne(int wfno);
	
	@Insert("insert into wfile values(wfile_seq.nextval, #{wno}, #{wfpath}, #{wffakefname}, #{wfrealfname}, #{wfext}")
	public int insert(WFileDto wFileDto);
	
	@Update("update wfile set wfpath=#{wfpath}, wffakefname=#{wffakefname}, wfrealfname=#{wfrealfname}, wfext=#{wfext} where wfno = #{wfno}")
	public int update(WFileDto wFileDto);
	
	@Delete("delete from wfile where wfno=#{wfno}")
	public int delete(int wfno);
	
}
