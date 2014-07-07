package com.oa.action.impl;

import java.util.Map;

import com.oa.action.IWorkTimeAction;
import com.oa.entity.Branchinfo;
import com.oa.entity.Departinfo;
import com.oa.entity.Manualsign;
import com.oa.service.IWorkTimeService;
import com.oa.util.Page;

public class WorkTimeAction extends Action4easyui<Manualsign> implements
		IWorkTimeAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Manualsign manualsignStart;
	private Manualsign manualsignEnd;
	private Branchinfo branchinfo;
	private Departinfo departinfo;

	@Override
	public String show() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showPage() {

		String isSuccess = ERROR;
		IWorkTimeService workTimeService = (IWorkTimeService) getBaseService();
		Page page = getBasePage();
		page.setPageSize(getRows());
		page.setCurrentIndex(getPage());
		workTimeService.pageShow(page, manualsignStart, manualsignEnd,
				branchinfo, departinfo);

		Map<String, Object> datas = getDatas();
		datas.put("total", page.getTotalRecords());
		datas.put("rows", page.getList());
		isSuccess = SUCCESS;
		branchinfo = null;
		departinfo = null;
		manualsignEnd = null;
		manualsignStart = null;
		return isSuccess;
	}

	/* getter && setter */
	public Manualsign getManualsignStart() {
		return manualsignStart;
	}

	public void setManualsignStart(Manualsign manualsignStart) {
		this.manualsignStart = manualsignStart;
	}

	public Manualsign getManualsignEnd() {
		return manualsignEnd;
	}

	public void setManualsignEnd(Manualsign manualsignEnd) {
		this.manualsignEnd = manualsignEnd;
	}

	public Branchinfo getBranchinfo() {
		return branchinfo;
	}

	public void setBranchinfo(Branchinfo branchinfo) {
		this.branchinfo = branchinfo;
	}

	public Departinfo getDepartinfo() {
		return departinfo;
	}

	public void setDepartinfo(Departinfo departinfo) {
		this.departinfo = departinfo;
	}

}
