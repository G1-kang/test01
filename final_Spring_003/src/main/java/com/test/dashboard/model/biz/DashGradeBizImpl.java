package com.test.dashboard.model.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dashboard.model.dao.DashGradeDao;
import com.test.dashboard.model.dto.DashGradeDto;


@Service
@Transactional
public class DashGradeBizImpl implements DashGradeBiz{

	@Autowired
	private DashGradeDao dashGradeDao;
	
	@Override
	public int delete(int dgno) {
		// TODO Auto-generated method stub
		return dashGradeDao.delete(dgno);
	}
	@Override
	public int insert(DashGradeDto dto) {
		// TODO Auto-generated method stub
		return dashGradeDao.insert(dto);
	}
	
	@Override
	public List<DashGradeDto> selectList(int dno) {
		// TODO Auto-generated method stub
		return dashGradeDao.selectList(dno);
	}
	
	@Override
	public DashGradeDto selectOne(Map<String, Object> params) {
		return dashGradeDao.selectOne(params);
	}
	
	@Override
	public int update(DashGradeDto dto) {
		// TODO Auto-generated method stub
		return dashGradeDao.update(dto);
	}
	
	
}
