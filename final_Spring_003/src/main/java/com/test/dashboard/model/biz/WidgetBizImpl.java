package com.test.dashboard.model.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dashboard.model.dao.WidgetDao;
import com.test.dashboard.model.dto.WRuleDto;
import com.test.dashboard.model.dto.WidgetDto;

@Service
@Transactional
public class WidgetBizImpl implements WidgetBiz{


	@Autowired
	private WidgetDao widgetDao;
	
	@Autowired
	private WRuleBiz wRuleBiz;
	
	@Override
	public int delete(int wno) {
		// TODO Auto-generated method stub
		return widgetDao.delete(wno);
	}
	@Override
	public int insert(WidgetDto widgetDto) {
		// TODO Auto-generated method stub
		
		int res = widgetDao.insert(widgetDto);
		
		if(res > 0) {
			
			List<WRuleDto> wRuleDtos = widgetDto.getRules();
			
			for(WRuleDto out : wRuleDtos) {
				
				out.setWno(widgetDto.getWno());
				
				int ruleRes = wRuleBiz.insert(out);
				
				if(ruleRes <= 0) {
					throw new TransactionException();
				}
				
			}
			widgetDto.setRules(wRuleBiz.selectList(widgetDto.getWno()));
			
		}
		
		return res;
	}
	
	@Override
	public List<WidgetDto> selectList(int dno, String mid) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dno", dno);
		params.put("mid", mid);
		
		List<WidgetDto> selectList = widgetDao.selectList(params);
		
		for(WidgetDto out : selectList) {
			out.setRules(wRuleBiz.selectList(out.getWno()));
		}
		
		return selectList;
	}
	@Override
	public WidgetDto selectOne(int wno) {
		// TODO Auto-generated method stub
		WidgetDto widgetDto = widgetDao.selectOne(wno);
		
		widgetDto.setRules(wRuleBiz.selectList(wno));
		
		return widgetDto;
	}
	@Override
	public int update(WidgetDto widgetDto) {
		// TODO Auto-generated method stub
		
		int res = widgetDao.update(widgetDto);
		
		if(res > 0) {
			
			wRuleBiz.wnoDelete(widgetDto.getWno());
			
			List<WRuleDto> wRuleDtos = widgetDto.getRules();
			
			for(WRuleDto out : wRuleDtos) {
				
				out.setWno(widgetDto.getWno());
				
				int ruleRes = wRuleBiz.insert(out);
				
				if(ruleRes <= 0) {
					throw new TransactionException();
				}
				
			}
			
		}
		
		return res;
		
	}
	
	@Override
	public int updateWdel(int wno) {
		// TODO Auto-generated method stub
		return widgetDao.updateWdel(wno);
	}
	
	
	@Override
	public int topLeftUpdate(WidgetDto widgetDto) {
		// TODO Auto-generated method stub
		return widgetDao.topLeftUpdate(widgetDto);
	}
	
	@Override
	public int widthHeightUpdate(WidgetDto widgetDto) {
		return widgetDao.widthHeightUpdate(widgetDto);
	}
	
	
	
}
