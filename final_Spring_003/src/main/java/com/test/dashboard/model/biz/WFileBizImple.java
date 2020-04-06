package com.test.dashboard.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dashboard.model.dao.WFileDao;
import com.test.dashboard.model.dto.WFileDto;

@Service
@Transactional
public class WFileBizImple implements WFileBiz{

	@Autowired
	private WFileDao wFileDao;
	
	@Override
	public int delete(int wfno) {
		// TODO Auto-generated method stub
		return wFileDao.delete(wfno);
	}
	
	@Override
	public int insert(WFileDto wFileDto) {
		// TODO Auto-generated method stub
		return wFileDao.insert(wFileDto);
	}
	
	@Override
	public List<WFileDto> selectByWNo(int wno) {
		// TODO Auto-generated method stub
		return wFileDao.selectByWNo(wno);
	}
	
	@Override
	public WFileDto selectOne(int wfno) {
		// TODO Auto-generated method stub
		return wFileDao.selectOne(wfno);
	}
	
	@Override
	public int update(WFileDto wFileDto) {
		// TODO Auto-generated method stub
		return wFileDao.update(wFileDto);
	}
	
}
