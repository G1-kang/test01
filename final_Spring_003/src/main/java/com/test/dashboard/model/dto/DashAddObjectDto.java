package com.test.dashboard.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class DashAddObjectDto {

	String dtitle, ddesc;
	DashBoardDto  dashBoardDto;
	DashMemberDto downer;
	List<DashMemberDto> members;
	DashGradeDto[] rules;
	
	
	
}
