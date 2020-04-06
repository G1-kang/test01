package com.test.dashboard.model.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dashboard.model.dao.WEditorDao;
import com.test.dashboard.model.dto.WEditorDto;

@Service
@Transactional
public class WEditorBizImpl implements WEditorBiz {
	
	@Autowired
	private WEditorDao dao;

	@Override
	public WEditorDto select(int weno) {
		
		return dao.select(weno);
	}

	@Override
	public int update(WEditorDto dto) {
		
		return dao.update(dto);
	}

	@Override
	public int insert(WEditorDto dto) {
		
		return dao.insert(dto);
	}

	@Override
	public int delete(int weno) {
		
		return dao.delete(weno);
	}

}
