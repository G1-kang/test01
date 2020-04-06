package com.test.dashboard.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("wMapDto")
public class WMapDto {
	
	private int wmapno;
	private int wno;
	private String wmapkeyword;
	private String wmapjibun;
	private String wmapaddr;
	private String wmaplat;
	private String wmaplng;
	private String wmapmemo;

}
