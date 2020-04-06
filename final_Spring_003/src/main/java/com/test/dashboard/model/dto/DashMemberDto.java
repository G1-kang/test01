package com.test.dashboard.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("dashMemberDto")
public class DashMemberDto {

	// 대시보드맴버 테이블 번호 
    private int dmno;

    // 대시보드 번호 
    private int dno;

    // 대시보드 참여 유저 아이디 
    private String mid;

    // 대시보드 참여 유저 등급 
    private int dgno;
    
    // 맴버 고유 색상
    private String dmcolor;
	
    // join 용
    private String mnick;

    private String mimgpath;

    private int dggrade;
    
    private String dgalias;
}

