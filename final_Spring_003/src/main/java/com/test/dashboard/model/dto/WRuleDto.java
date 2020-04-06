package com.test.dashboard.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("wRuleDto")
public class WRuleDto {

	// 위젯 룰 테이블 번호 
    private int wrno;

    // 소속 위젯 번호 
    private int wno;

    // 권한 종류 group/individual
    private String wrcategory;

    // 권한 속성 4 : read / 6 : read&write / 7 : read & write & delete
    private int wrrwd;

    // 적용 인원 individual 일 경우 개별 적용 룰
    private String mid;

    // 그룹 최소 권한 등급 group 일 경우 0~9999
    private int wrmin;

    // 그룹 최대 권한 등급 group 일 경우 0~9999
    private int wrmax;

    // join 용
    private String mnick;
	
    private String dmcolor;
    
    private String dgcolor;
    
    private String mimgpath;
    
}
