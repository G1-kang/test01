package com.test.dashboard.model.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("memberDto")
@Data
public class MemberDto {
	
	// 회원 정보 
    private int mno;

    // 회원 아이디 
    private String mid;

    // 회원 닉네임 
    private String mnick;

    // 회원 이름 
    private String mname;

    // 회원 비밀번호 
    private String mpw;

    // 회원 이메일 
    private String memail;

    // 회원 주소 
    private String maddr;

    // 회원 전화 번호 
    private String mphone;

    // 회원 소개 
    private String mabout;

    // 회원 사진 패스 
    private String mimgpath;

    // 회원 등급 
    private String mgrade;

    // 회원 가입일 
    private Date mcreatedate;

    // 회원 탈퇴 유무 Check(Y,N)
    private String mdel;
	
    // 회원 접근 플랫폼
    private String mplatform;
    
}
