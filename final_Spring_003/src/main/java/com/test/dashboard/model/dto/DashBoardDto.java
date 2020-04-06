package com.test.dashboard.model.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("dashBoardDto")
public class DashBoardDto {

	    // 대시보드 테이블 번호 
	    private int dno;

	    // 대시보드 명칭 
	    private String dtitle;

	    // 대시보드 소유주 
	    private String mid;

	    // 대시보드 설명 
	    private String ddesc;

	    // 대시보드 생성일 
	    private Date dcreatedate;

	    // 대시보드 수정일 
	    private Date dmodifydate;
	    
	    // 대시보드 삭제 유무 
	    private String ddel;	    
	
	    // join용 -- 외부 테이블 변수
	    private String mnick;
	    
	    private String mimgpath;
	    
}
