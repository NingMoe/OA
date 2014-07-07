package com.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.oa.entity.Loginlog;
import com.oa.service.ILogService;

public class LogServiceImpl extends BaseServiceImpl<Loginlog> implements ILogService{


	@Override
	public Map deleteAll(Loginlog loginlog) {
		// TODO Auto-generated method stub
		String hql="delete Loginlog";
		List<Loginlog> list=getDao().findByHql(hql);

		Map datas = new HashMap();
		
		datas.put("total", list.size());
		
		datas.put("rows", list);
		
		return datas;
	}
	
	@Override
	public Map searchBytime(String loginTime,String nextTime) {
		// TODO Auto-generated method stub
		
		System.out.println("根据时间查询..........");
		System.out.println();
		String hql="from Loginlog where loginTime BETWEEN "
				+"'"+loginTime+"' AND '" +nextTime+"'";
		List<Loginlog> list=getDao().findByHql(hql);

		Map datas = new HashMap();
		
		datas.put("total", list.size());
		
		datas.put("rows", list);
		
		return datas;
	}
	


}
