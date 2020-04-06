package com.test.dashboard.model.biz;

import com.test.dashboard.model.dto.WEditorDto;

public interface WEditorBiz {
	
	public WEditorDto select(int weno);
	public int update(WEditorDto dto);
	public int insert(WEditorDto dto);
	public int delete(int weno);

}
