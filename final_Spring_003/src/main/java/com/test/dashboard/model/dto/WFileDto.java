package com.test.dashboard.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("wFileDto")
public class WFileDto {

	// 파일 테이블 번호 
    private int wfno;

    // 소속 위젯 번호 
    private int wno;

    // 파일 경로 
    private String wfpath;

    // 파일저장첨부명 
    private String wffakename;

    // 파일실제첨부명 
    private String wfrealname;

    // 파일첨부확장자 
    private String wfext;
	
}
