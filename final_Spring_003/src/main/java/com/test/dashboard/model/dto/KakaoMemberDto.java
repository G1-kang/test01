package com.test.dashboard.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("kakaoMemberDto")
public class KakaoMemberDto {

	String access_token, token_type, refresh_token, expires_in, scope, refresh_token_expires_in;
	
}
