package com.test.dashboard.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dashboard.model.dao.WMapDao;
import com.test.dashboard.model.dto.WMapDto;

@Service
@Transactional
public class WMapBizImpl implements WMapBiz {

	@Autowired
	private WMapDao dao;
	
	@Override
	public List<WMapDto> selectList(int wno){
		return dao.selectList(wno);
	}
	
//	@Override
//	public List<WMapDto> selectList(){
//		return dao.selectList();
//	}

	@Override
	public WMapDto select(int wmapno) {

		return dao.select(wmapno);
	}

	@Override
	public int insert(WMapDto dto) {
		
		return dao.insert(dto);
	}

	@Override
	public int update(WMapDto dto) {
		
		return dao.update(dto);
	}

	@Override
	public int delete(int wmapno) {
		
		return dao.delete(wmapno);
	}

}
