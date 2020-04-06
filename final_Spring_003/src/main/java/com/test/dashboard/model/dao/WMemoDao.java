package com.test.dashboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.test.dashboard.model.dto.WMemoDto;

@Repository
public interface WMemoDao {

	@Select("select * from wmemo")
	public List<WMemoDto> selectList();
	
	@Select("select * from wmemo where wno = #{wno}")
	public WMemoDto selectOne(int wno);
	
	@SelectKey(statement = "select wmemo_seq.nextval from dual", keyProperty = "wmno", resultType = Integer.class, before = true)
	@Insert("insert into wmemo values(#{wmno}, #{wno}, #{wmcontent}, sysdate)")
	public int insert(WMemoDto wMemoDto);
	
	@Update("update wmemo set wmcontent = #{wmcontent}, wmwritedate = sysdate where wno = #{wno} and wmno = #{wmno}")
	public int update(WMemoDto wMemoDto);
	
	@Delete("delete from wmemo where wno = #{wno}")
	public int delete(int wno);
	
}
