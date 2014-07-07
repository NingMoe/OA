package com.oa.action.impl;

import java.util.List;

import com.oa.action.ILogAction;
import com.oa.entity.Loginlog;
import com.oa.service.impl.LogServiceImpl;

public class LogAction extends Action4easyui<Loginlog> implements ILogAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Loginlog loginlog;
	private List<Loginlog> listlog;
	private String loginTime;
	private String nextTime;
	

	public String getNextTime() {
		return nextTime;
	}

	public void setNextTime(String nextTime) {
		this.nextTime = nextTime;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String remove() {
		// TODO Auto-generated method stub
		Loginlog loginlog = getBaseService().getById(Integer.parseInt(getId()));
		getBaseService().delete(loginlog);
		return SUCCESS;
	}

	@Override
	public String deleteAll() {
		// TODO Auto-generated method stub
		setDatas(((LogServiceImpl)getBaseService()).deleteAll(loginlog));
		return SUCCESS;
	}

	@Override
	public String search() {
		// TODO Auto-generated method stub
		setDatas(((LogServiceImpl)getBaseService()).searchBytime(loginTime,nextTime));
		return SUCCESS;
	}
	
	public Loginlog getLoginlog() {
		return loginlog;
	}

	public void setLoginlog(Loginlog loginlog) {
		this.loginlog = loginlog;
	}

	@Override
	public String show() {
		// TODO Auto-generated method stub
		return null;
	}


}
