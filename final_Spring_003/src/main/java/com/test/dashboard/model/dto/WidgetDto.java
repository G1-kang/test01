package com.test.dashboard.model.dto;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("widgetDto")
public class WidgetDto {
	
	// 위젯 설정 테이블 번호 
    private int wno;

    // 위젯이 속한 대시보드 테이블 번호 
    private int dno;

    // 위젯 내용 종류 
    private String wcategory;

    // 위젯 제목 
    private String wtitle;

    // 위젯 소유주 
    private String mid;

    // 위젯 X축 
    private int wleft;

    // 위젯 Y축 
    private int wtop;

    // 위젯 가로 길이 
    private int wwidth;

    // 위젯 세로 길이 
    private int wheight;

    // 위젯 Z축 
    private int wzindex;

    // 위젯 내용 배경색 
    private String wcontentcolor;

    // 위젯 제목 배경색 
    private String wtitlecolor;

    // 위젯 포지션 
    private String wposition;

    // 위젯 생성시기 
    private Date wcreatedate;
    
    // 위젯 삭제 유무 
    private String wdel;
	
    
    // join 용
    private List<WRuleDto> rules;
    
    private WMemoDto wmemo;
    
    private WChatDto wchat;
    
}
