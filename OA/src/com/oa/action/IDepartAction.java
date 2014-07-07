package com.oa.action;

import com.oa.service.impl.DepartServiceImpl;

public interface IDepartAction extends IBaseAction {
	
	public DepartServiceImpl getReallyService();
	
	public String checkDepartName();
}
