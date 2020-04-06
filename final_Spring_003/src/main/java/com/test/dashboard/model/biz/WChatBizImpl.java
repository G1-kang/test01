package com.test.dashboard.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dashboard.model.dao.WChatDao;
import com.test.dashboard.model.dto.WChatDto;

@Service
@Transactional
public class WChatBizImpl implements WChatBiz {

	@Autowired
	private WChatDao wChatDao;
	
	@Override
	public WChatDto selectOne(int wno) {
		// TODO Auto-generated method stub
		return wChatDao.selectOne(wno);
	}

	@Override
	public List<WChatDto> selectList(int dno) {
		// TODO Auto-generated method stub
		return wChatDao.selectList(dno);
	}

	@Override
	public int insert(WChatDto wChatDto) {
		// TODO Auto-generated method stub
		
		return wChatDao.insert(wChatDto);
	}
	
	@Override
	public int delete(int wno) {
		// TODO Auto-generated method stub
		return 0;
	}

}
