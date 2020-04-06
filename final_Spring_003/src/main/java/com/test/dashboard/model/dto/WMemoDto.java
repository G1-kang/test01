package com.test.dashboard.model.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("wMemoDto")
public class WMemoDto {

	// 메모 테이블 번호 
    private int wmno;

    // 소속 위젯 번호 
    private int wno;

    // 메모 내용 
    private String wmcontent;

    // 메모 작성일 
    private Date wmwritedate;
	
}
