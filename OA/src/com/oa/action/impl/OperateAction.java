package com.oa.action.impl;

import com.oa.action.IOperateAction;
import com.oa.entity.Operatelog;
import com.oa.service.impl.OperateServiceImpl;

public class OperateAction extends Action4easyui<Operatelog> implements IOperateAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Operatelog operatelog;
	private String operateTime;

	private String nextTime;

	@Override
	public String add() {
		// TODO Auto-generated method stub
		return super.add();
	}
	
	@Override
	public String update() {
		// TODO Auto-generated method stub
		return super.update();
	}
	@Override
	public String remove() {
		// TODO Auto-generated method stub
		Operatelog operatelog=getBaseService().getById(Integer.parseInt(getId()));
		getBaseService().delete(operatelog);
		return SUCCESS;
	}
	@Override
	public String Search() {
		// TODO Auto-generated method stub
		setDatas(((OperateServiceImpl)getBaseService()).searchBytime(operateTime, nextTime));
		return SUCCESS;
	}


	public String getOperateTime() {
		return operateTime;
	}


	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getNextTime() {
		return nextTime;
	}

	public void setNextTime(String nextTime) {
		this.nextTime = nextTime;
	}

	public Operatelog getOperatelog() {
		return operatelog;
	}

	public void setOperatelog(Operatelog operatelog) {
		this.operatelog = operatelog;
	}

	@Override
	public String show() {
		// TODO Auto-generated method stub
		return null;
	}
}
