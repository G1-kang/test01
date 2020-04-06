package com.test.dashboard.model.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.test.dashboard.model.dto.WEditorDto;

@Repository
public interface WEditorDao {
	
	@Select("SELECT WENO, WNO, WDITOR FROM WEDITOR WHERE WENO=#{weno}")
	public WEditorDto select(int weno);
	
	@Update("UPDATE WEDITOR SET WECONTENT=#{wecontent} WHERE WENO=#{weno}")
	public int update(WEditorDto dto);
	
	@Insert("INSERT INTO WEDITOR VALUES(WMEDITOR_SEQ.NEXTVAL, 1, #{wecontent})")
	public int insert(WEditorDto dto);
	
	@Delete("DELETE FROM WEDITOR WHERE WENO=#{weno}")
	public int delete (int weno);

}
