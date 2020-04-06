package com.test.dashboard.model.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dashboard.common.util.Util;
import com.test.dashboard.model.dao.MemberDao;
import com.test.dashboard.model.dto.MemberDto;

@Service
@Transactional
public class MemberBizImpl implements MemberBiz{

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public int delete(int mno) {
		// TODO Auto-generated method stub
		return memberDao.delete(mno);
	}
	
	@Override
	public int insert(MemberDto memberDto) {
		// TODO Auto-generated method stub
//		memberDto.setMabout(Util.brChange(memberDto.getMabout()));
		
		if(memberDto.getMabout() != null) {
			memberDto.setMabout(Util.brChange(memberDto.getMabout()));
		}
		return memberDao.insert(memberDto);
	}
	
	@Override
	public MemberDto selectByIdAndPw(String mid, String mpw) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("mid", mid);
		params.put("mpw", mpw);
		
		return memberDao.selectByIdAndPw(params);
	}
	
	@Override
	public MemberDto pwSearchCheck(MemberDto memberDto) {
		return memberDao.pwSearchCheck(memberDto);
	}
	
	
	@Override
	public MemberDto idSearchByName(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return memberDao.idSearchByName(memberDto);
	}
	
	@Override
	public MemberDto selectById(String mid) {
		// TODO Auto-generated method stub
		return memberDao.selectById(mid);
	}
	
	@Override
	public MemberDto selectByMNo(int mno) {
		// TODO Auto-generated method stub
		return memberDao.selectByMNo(mno);
	}
	
	@Override
	public List<MemberDto> selectList() {
		// TODO Auto-generated method stub
		return memberDao.selectList();
	}
	
	@Override
	public int update(MemberDto memberDto) {
		// TODO Auto-generated method stub
		if(memberDto.getMabout() != null) {
			memberDto.setMabout(Util.brChange(memberDto.getMabout()));
		}
		return memberDao.update(memberDto);
	}
	
	@Override
	public int passwordUpdate(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return memberDao.passwordUpdate(memberDto);
	}

	@Override
	public MemberDto selectByNick(String mnick) {
		// TODO Auto-generated method stub
		return memberDao.selectByNick(mnick);
	}

	@Override
	public int selectByName(String mname) {
		// TODO Auto-generated method stub
		return memberDao.selectByName(mname);
	}
	
	@Override
	public int maboutUpdate(MemberDto memberDto) {
		// TODO Auto-generated method stub
		if(memberDto.getMabout() != null) {
			memberDto.setMabout(Util.brChange(memberDto.getMabout()));
		}
		return memberDao.maboutUpdate(memberDto);
	}
}
