package com.test.dashboard.model.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("wboardDto")
public class WboardDto {
	private int wbtodono; //위젯보드 해야할일 전체 번호
	private int wno ; //위젯번호
	private int dno; // 고유넘버(모든사람이 공통으로 가짐)위젯이 속한 대시보드 테이블 번호
	private int dgno ; //대시보드 맴버 권한 그룹 넘버
	private String mid;
	private String wbtodo ; //Y : 한일 N : 해야 할 일 , 전체할일은 
	private String wbtitle ;
	private String wbcontent ;
	private String wfno_list;//파일 테이블 번호
	private String wbstartdate;
	private String wbenddate ;
	private String wbcolor ;//할일게시판 색상
	

}
