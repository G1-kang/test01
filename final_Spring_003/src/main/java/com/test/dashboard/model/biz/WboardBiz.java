package com.test.dashboard.model.biz;

import java.util.List;

import com.test.dashboard.model.dto.WboardDto;

public interface WboardBiz {
	
	//전체 목록 조회
	public List<WboardDto> boardListAll(int wno);
	
	//내글만 보기 
	public List<WboardDto> boardMyList(WboardDto dto);
	
	//연습용 
	public List<WboardDto> boardList();
	
	//글 상세보기 
	public WboardDto wSelectOne(int wbtodono);
	
	//게시물 작성 
	public int wbinsert(WboardDto dto);
	
	//게시물 삭제 
	public int wDelete(int wbtodono);
	
	//게시물 수정
	public int summerUpdate(WboardDto dto);
	
	//달력 자료 뿌려주기
	public List<WboardDto> wbdatesend(WboardDto dto);
	
}
