package com.test.dashboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import com.test.dashboard.model.dto.WChatDto;

@Repository
public interface WChatDao {

	@Select("select * from wchat where wno = #{wno}")
	public WChatDto selectOne(int wno);

	@Select("select * from wchat where wno in (select * from widget where dno = #{dno})")
	public List<WChatDto> selectList(int dno);
	
	@SelectKey(statement = "select wchat_SEQ.nextval from dual", keyProperty = "wcno", resultType = Integer.class, before = true)
	@Insert("insert into wchat values(#{wcno}, #{wno}, #{wcpath})")
	public int insert(WChatDto wChatDto);
	
	@Delete("delete from wchat where wno = #{wno}")
	public int delete(int wno);
}
