package com.test.dashboard.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dashboard.model.dao.WboardDao;
import com.test.dashboard.model.dto.WboardDto;

@Service
@Transactional
public class WboardBizImpl implements WboardBiz{

	@Autowired
	private WboardDao wboardDao; 
	
	@Override
	public List<WboardDto> boardListAll(int wno) {
		return wboardDao.boardListAll(wno);
	}

	@Override
	public List<WboardDto> boardList() {
		System.out.println("dddd");
		return wboardDao.boardList();
	}

	@Override
	public WboardDto wSelectOne(int wbtodono) {
		WboardDto wboardDto = null;
		
		try {
			wboardDto = wboardDao.wSelectOne(wbtodono);
		} catch (Exception e) {
			System.out.println("wboardDao biz 에러 발생");
			e.printStackTrace();
		}
		//System.out.println(wbtodono);
		
		return wboardDao.wSelectOne(wbtodono);
	}

	//게시물 작성 
	@Override
	public int wbinsert(WboardDto dto) {
		

		return wboardDao.wbinsert(dto);
	}

	
	//게시물 삭제 
	@Override
	public int wDelete(int wbtodono) {
		return wboardDao.wDelete(wbtodono);
	}

	//게시물 수정 
	@Override
	public int summerUpdate(WboardDto dto) {

		try {
			wboardDao.summerUpdate(dto);
		} catch (Exception e) {
			System.out.println("수정에서 에러남 -비즈");
			e.printStackTrace();
		}
		
		return wboardDao.summerUpdate(dto);
	}

	//내게시글보기 
	@Override
	public List<WboardDto> boardMyList(WboardDto dto) {
		return wboardDao.boardMyList(dto);
	}

	//달력 다료 뿌려주기 
	@Override
	public List<WboardDto> wbdatesend(WboardDto dto) {
		return wboardDao.wbdatesend(dto);
	}

}
