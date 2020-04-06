package com.test.dashboard.model.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("crawlingDto")
@Data
public class CrawlingDto {

	// SNS 종류
	private String wcrwlfrom;

	// 글 작성자
	private String wcrwlwriter;

	// 글 내용
	private String wcrwlcontent;

	// 작성일
	private String wcrwldate;

	// 원본 링크
	private String wcrwllink;
	

}
