package com.test.dashboard.model.biz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dashboard.model.dao.DashMemberDao;
import com.test.dashboard.model.dto.DashMemberDto;

@Service
@Transactional(rollbackFor = SQLException.class)
public class DashMemberBizImpl implements DashMemberBiz {

	@Autowired
	private DashMemberDao dashMemberDao;
	
	@Override
	public int delete(int dmno) {
		// TODO Auto-generated method stub
		return dashMemberDao.delete(dmno);
	}
	
	@Override
	public int insert(List<DashMemberDto> params, int dno) throws SQLException {
		// TODO Auto-generated method stub
		int res = 0;
		for(DashMemberDto param : params) {
			param.setDno(dno);
			res = dashMemberDao.insert(param);
		}
		if(res == 0) {
			throw new SQLException();
		}
		return res;
	}
	
	@Override
	public DashMemberDto selectById(int dno, String mid) {
		// TODO Auto-generated method stub
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dno", dno);
		params.put("mid", mid);
		
		return dashMemberDao.selectById(params);
	}
	
	@Override
	public DashMemberDto selectByNo(int dmno) {
		// TODO Auto-generated method stub
		return dashMemberDao.selectByNo(dmno);
	}
	
	@Override
	public List<DashMemberDto> selectList(int dno) {
		// TODO Auto-generated method stub
		return dashMemberDao.selectList(dno);
	}
	@Override
	public int update(DashMemberDto dto) {
		// TODO Auto-generated method stub
		return dashMemberDao.update(dto);
	}
	
}
