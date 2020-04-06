package com.test.dashboard.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("dashGradeDto")
public class DashGradeDto {

	// 대시보드 맴버 등급 테이블 번호 
    private int dgno;

    // 대시보드 테이블 번호 
    private int dno;

    // 대시보드 테이블 번호 0~9999
    private int dggrade;

    // 등급 별칭 
    private String dgalias;
	
    // 등급 컬러링
    private String dgcolor;
}
