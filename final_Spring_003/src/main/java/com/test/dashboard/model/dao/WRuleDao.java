package com.test.dashboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.test.dashboard.model.dto.WRuleDto;

@Repository
public interface WRuleDao {

	@Select("select * from wrule where wno = #{wno}")
	List<WRuleDto> selectList(int wno);
	
	@Select("select * from wrule where wrno = #{wrno}")
	WRuleDto selectOne(int wrno);
	
	@SelectKey(statement = "select wrule_seq.nextval from dual", keyProperty = "wrno", resultType = Integer.class, before = true)
	@Insert("insert into wrule values(#{wrno},#{wno},#{wrcategory},#{wrrwd},#{mid},#{wrmin},#{wrmax})")
	int insert(WRuleDto wRuleDto);
	
	@Update("update wrule set wrcategory=#{wrcategory}, wrrwd=#{wrrwd}, mid=#{mid}, wrmin=#{wrmin}, wrmax=#{wrmax} where wrno = #{wrno}")
	int update(WRuleDto wRuleDto);
	
	@Delete("delete from wrule where wrno = #{wrno}")
	int delete(int wrno);

	@Delete("delete from wrule where wno = #{wno}")
	int wnoDelete(int wno);
}
