package com.oa.action.impl;

import com.oa.action.IMeetingAction;
import com.oa.entity.Meetinginfo;

public class MeetingAction extends Action4easyui<Meetinginfo> implements
		IMeetingAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String add() {
		return null;
	}

	@Override
	public String remove() {
		return null;
	}

	@Override
	public String update() {
		return null;
	}

	@Override
	public String show() {
		return null;
	}

	@Override
	public String showAll() {
		try {
			setBaseList(getBaseService().getAllObject());
			this.getDatas().put("meetingInfo", this.getBaseList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
