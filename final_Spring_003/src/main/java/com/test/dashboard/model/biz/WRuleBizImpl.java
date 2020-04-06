package com.test.dashboard.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dashboard.model.dao.WRuleDao;
import com.test.dashboard.model.dto.WRuleDto;

@Service
public class WRuleBizImpl implements WRuleBiz{

	@Autowired
	private WRuleDao wRuleDao;
	
	@Override
	public int delete(int wrno) {
		// TODO Auto-generated method stub
		return wRuleDao.delete(wrno);
	}
	
	@Override
	public int insert(WRuleDto wRuleDto) {
		// TODO Auto-generated method stub
		return wRuleDao.insert(wRuleDto);
	}
	
	@Override
	public List<WRuleDto> selectList(int wno) {
		// TODO Auto-generated method stub
		return wRuleDao.selectList(wno);
	}
	
	@Override
	public WRuleDto selectOne(int wrno) {
		// TODO Auto-generated method stub
		return wRuleDao.selectOne(wrno);
	}
	
	@Override
	public int update(WRuleDto wRuleDto) {
		// TODO Auto-generated method stub
		return wRuleDao.update(wRuleDto);
	}
	
	@Override
	public int wnoDelete(int wno) {
		// TODO Auto-generated method stub
		return wRuleDao.wnoDelete(wno);
	}
	
}
