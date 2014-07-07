package com.oa.service;

import java.util.Map;

import com.oa.entity.Loginlog;


public interface ILogService extends IBaseService<Loginlog>{

	public Map deleteAll(Loginlog loginlog);
	
	public Map searchBytime(String loginTime,String nextTime);
	
}
