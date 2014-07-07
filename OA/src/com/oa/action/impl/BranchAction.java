package com.oa.action.impl;

import com.oa.action.IBranchAction;
import com.oa.entity.Branchinfo;
import com.oa.entity.Userinfo;
import com.oa.service.IBranchService;
import com.oa.util.Message;

public class BranchAction extends Action4easyui<Branchinfo> implements
		IBranchAction {

	private Integer branchId;
	private String branchName;
	private String branchShortName;

	private Message msg;

	@Override
	public String add() {
		getBaseService().save(new Branchinfo(branchName, branchShortName));
		return SUCCESS;
	}

	@Override
	public String remove() {
		return getBranchService().delete(Integer.parseInt(getId()),
				(Userinfo) getSession().get("user"), getMsg());
	}

	@Override
	public String update() {
		// TODO Auto-generated method stub
		getBaseService().update(
				new Branchinfo(branchId, branchName, branchShortName));
		return SUCCESS;
	}

	@Override
	public String show() {
		// TODO Auto-generated method stub
		return null;
	}

	public IBranchService getBranchService() {
		return (IBranchService) getBaseService();
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchShortName(String branchShortName) {
		this.branchShortName = branchShortName;
	}

	public String getBranchShortName() {
		return branchShortName;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public Message getMsg() {
		return msg == null ? msg = new Message() : msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

}