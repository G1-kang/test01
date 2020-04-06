package com.test.dashboard.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dashboard.model.dao.WMemoDao;
import com.test.dashboard.model.dto.WMemoDto;

@Service
@Transactional
public class WMemoBizImpl implements WMemoBiz{

	@Autowired
	private WMemoDao wMemoDao;
	
	@Override
	public int delete(int wno) {
		// TODO Auto-generated method stub
		return wMemoDao.delete(wno);
	}
	
	@Override
	public int insert(WMemoDto wMemoDto) {
		// TODO Auto-generated method stub
		wMemoDto.setWmcontent(wMemoDto.getWmcontent().replaceAll("(\r\n|\r|\n|\n\r)", " "));
		return wMemoDao.insert(wMemoDto);
	}
	@Override
	public List<WMemoDto> selectList() {
		// TODO Auto-generated method stub
		return wMemoDao.selectList();
	}
	
	@Override
	public WMemoDto selectOne(int wno) {
		// TODO Auto-generated method stub
		return wMemoDao.selectOne(wno);
	}
	
	@Override
	public int update(WMemoDto wMemoDto) {
		// TODO Auto-generated method stub
		return wMemoDao.update(wMemoDto);
	}
	
	
}

