package com.test.dashboard.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.test.dashboard.model.dto.WidgetDto;

@Repository
public interface WidgetDao {
	
	/*
	@Select("with mygrade as (select dggrade from dashgrade where dgno = (select dmdgno from dashmember where dmdno = #{dmdno} and dmmid = #{dmmid})) select * from widget where wdno = #{wdno} and wmingrade <= mygrade.dggrade and wmaxgrade >= mygrade.dggrade")
	public List<WidgetDto> selectList(Map<String, Object> params);
	*/
	@Select("with grade as (select dggrade from dashgrade where dgno = (select dgno from dashmember where dno = #{dno} and mid = #{mid})) select * from widget where wdel = 'N' and wno in (select distinct wr.wno from widget w inner join wrule wr on(w.wno = wr.wno) where w.dno = #{dno} and (wr.mid = #{mid} or (select * from grade) between wr.wrmin and wr.wrmax))")
	public List<WidgetDto> selectList(Map<String, Object> params);
	
	@Select("select * from widget where wno = #{wno}")
	public WidgetDto selectOne(int wno);
	
	@SelectKey(statement = "select widget_seq.nextval from dual", keyProperty = "wno", resultType = Integer.class, before = true)
	@Insert("insert into widget values(#{wno}, #{dno}, #{wcategory}, #{wtitle}, #{mid}, #{wleft}, #{wtop}, #{wwidth}, #{wheight}, #{wzindex}, #{wcontentcolor}, #{wtitlecolor}, #{wposition}, sysdate, 'N')")
	public int insert(WidgetDto widgetDto);
	
	@Update("update widget set wtitle=#{wtitle}, wleft=#{wleft}, wtop=#{wtop}, wwidth=#{wwidth}, wcategory=#{wcategory}, wzindex=#{wzindex}, wcontentcolor=#{wcontentcolor}, wtitlecolor=#{wtitlecolor}, wposition=#{wposition} where wno=#{wno}")
	public int update(WidgetDto widgetDto);
	
	@Update("update widget set wdel='Y' where wno = #{wno}")
	public int updateWdel(int wno);
	
	@Update("update widget set wtop=#{wtop}, wleft=#{wleft} where wno = #{wno}")
	public int topLeftUpdate(WidgetDto widgetDto);
	
	@Update("update widget set wwidth=#{wwidth}, wheight=#{wheight} where wno = #{wno}")
	public int widthHeightUpdate(WidgetDto widgetDto);
	
	@Delete("delete from widget where wno=#{wno}")
	public int delete(int wno);
	
}
