package com.test.dashboard.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dashboard.common.util.Util;
import com.test.dashboard.model.dao.DashBoardDao;
import com.test.dashboard.model.dto.DashBoardDto;

@Service
@Transactional
public class DashBoardBizImpl implements DashBoardBiz{

	@Autowired
	private DashBoardDao dashBoardDao;
	
	@Override
	public int update(DashBoardDto dto) {
		// TODO Auto-generated method stub
		return dashBoardDao.update(dto);
	}
	
	@Override
	public int delete(int dno) {
		// TODO Auto-generated method stub
		return dashBoardDao.delete(dno);
	}
	@Override
	public int insert(DashBoardDto dto) {
		// TODO Auto-generated method stub
		
		dto.setDdesc(Util.brChange(dto.getDdesc()));
		
		return dashBoardDao.insert(dto);
	}
	
	@Override
	public List<DashBoardDto> selectByBelong(String mid) {
		// TODO Auto-generated method stub
		return dashBoardDao.selectByBelong(mid);
	}
	
	@Override
	public List<DashBoardDto> selectByOwner(String mid) {
		// TODO Auto-generated method stub
		return dashBoardDao.selectByOwner(mid);
	}
	
	@Override
	public int updateDel(int dno) {
		// TODO Auto-generated method stub
		return dashBoardDao.updateDel(dno);
	}
	
	@Override
	public DashBoardDto selectOne(int dno) {
		// TODO Auto-generated method stub
		return dashBoardDao.selectOne(dno);
	}
	
	
	
}
