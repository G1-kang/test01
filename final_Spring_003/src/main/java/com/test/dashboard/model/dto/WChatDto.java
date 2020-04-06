package com.test.dashboard.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("wChatDto")
public class WChatDto {
	
	// 채팅 고유번호 
    private int wcno;

    // 소속 위젯 번호 
    private int wno;

    // 채팅 로그 패스 
    private String wcpath;
	
    // logUpdate 용
    private String mnick;
    private String msg;
    
}
