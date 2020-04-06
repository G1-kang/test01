package com.test.dashboard.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.test.dashboard.model.dto.DashGradeDto;

@Repository
public interface DashGradeDao {

	// 특정 대쉬보드의 등급 목록
	@Select("select * from dashgrade where dno = #{dno} order by dggrade")
	public List<DashGradeDto> selectList(int dno);
	
	@Select("select * from dashgrade where dgno = (select dgno from dashmember where dno = #{dno} and mid = #{mid})")
	public DashGradeDto selectOne(Map<String, Object> params);
	
	@Insert("insert into dashgrade values(dashgrade_seq.nextval, #{dno}, #{dggrade}, #{dgalias}, #{dgcolor})")
	public int insert(DashGradeDto dto);
	
	@Update("update dashgrade set dggrade = #{dggrade}, dgalias = #{dgalias}, dgcolor = #{dgcolor} where dgno = #{dgno}")
	public int update(DashGradeDto dto);
	
	@Delete("delete from dashgrade where dgno = #{dgno}")
	public int delete(int dgno);
	
	
}
