package com.test.dashboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.test.dashboard.model.dto.DashBoardDto;

@Repository
public interface DashBoardDao {

	//본인 소유 보드 조회
	@Select("select * from dashboard where mid = #{mid} and ddel = 'N'")
	public List<DashBoardDto> selectByOwner(String mid);
	
	//본인 소속 보드 조회
	@Select("select d.*, m.mnick, m.mimgpath from dashboard d inner join member m on(d.mid = m.mid) where dno in (select dno from dashmember dm where dm.mid = #{mid}) and ddel = 'N' order by dno desc")
	public List<DashBoardDto> selectByBelong(String mid);

	//보드 입장
	@Select("select * from dashboard d inner join member m on (d.mid = m.mid) where d.dno = #{dno} and d.ddel = 'N'")
	public DashBoardDto selectOne(int dno);
	
	@SelectKey(statement = "select dashboard_seq.nextval from dual", keyProperty = "dno", resultType = Integer.class, before = true)
	@Insert("insert into dashboard values(#{dno}, #{dtitle}, #{mid}, #{ddesc}, sysdate, null, 'N')")
	public int insert(DashBoardDto dto);
	
	@Update("update dashboard set dtitle = #{dtitle}, ddesc = #{ddesc}, dmodifydate = sysdate where dno = #{dno}")
	public int update(DashBoardDto dto);
	
	@Update("update dashboard set ddel = 'Y' where dno = #{dno}")
	public int updateDel(int dno);
	
	@Delete("delete from dashboard where dno = #{dno}")
	public int delete(int dno);
		
}

