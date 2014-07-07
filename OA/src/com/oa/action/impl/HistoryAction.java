package com.oa.action.impl;

import java.util.HashSet;
import java.util.Set;

import com.oa.action.IHistoryAction;
import com.oa.entity.Branchinfo;
import com.oa.entity.Departinfo;
import com.oa.entity.Manualsign;
import com.oa.entity.Userinfo;
import com.oa.service.IBranchService;
import com.oa.service.IDepartService;
import com.oa.service.IHistoryService;
import com.oa.service.impl.HistoryServiceImpl;

public class HistoryAction extends Action4easyui<Manualsign> implements
		IHistoryAction {

	private IBranchService branchService;
	private IDepartService departService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// easyui 前台过来的 rows：多少条数据，page：第几页
	private Manualsign manualsignStart;
	private Manualsign manualsignEnd;

	private Branchinfo branchinfo;// 机构
	private Departinfo departinfo;// 部门

	private Userinfo userinfo;

	@Override
	public String show() {
		return null;
	}

	@Override
	public String showPage() {
		IHistoryService historyServiceImpl = (HistoryServiceImpl) getBaseService();

		// 在没有条件情况下，查询所有的考勤 即调用父类的方法
		if (departinfo == null && branchinfo == null && manualsignStart == null
				&& manualsignEnd == null && userinfo == null) {
			super.showPage();
		}
		// 查询条件存在
		else {
			super.getBasePage().setCurrentIndex(super.getPage());
			super.getBasePage().setPageSize(super.getRows());

			/* 装备page */
			Set<Userinfo> userinfos = null;

			if (userinfo == null) {
				// 取得某机构下某部门下的所有用户 把用户 给
				if (departinfo != null) {
					System.out
							.println("departinfo:" + departinfo.getDepartId());
					System.out
							.println("branchinfo:" + branchinfo.getBranchId());
					userinfos = departService.getDeparts(departinfo, userinfo);
				}
				// 取得该机构所有部门下的用户
				else if (branchinfo != null) {
					System.out
							.println("branchinfo:" + branchinfo.getBranchId());
					userinfos = branchService.getBranchsFieldUser(branchinfo);
				}
			} else {
				userinfos = new HashSet<Userinfo>();
				userinfos.add(userinfo);
			}
			// 在这里进行最终的 考勤记录 encapsulation(封装)
			if ((manualsignStart != null && manualsignEnd != null)
					|| userinfo != null) {
				historyServiceImpl.packPage(getBasePage(), manualsignStart,
						manualsignEnd, userinfos);// 装备
			}

			getDatas().put("total", super.getBasePage().getTotalRecords());
			getDatas().put("rows", super.getBasePage().getList());
		}

		manualsignStart = null;
		manualsignEnd = null;
		branchinfo = null;
		departinfo = null;
		userinfo = null;

		return SUCCESS;
	}

	/* set && get */
	public IBranchService getBranchService() {
		return branchService;
	}

	public void setBranchService(IBranchService branchService) {
		this.branchService = branchService;
	}

	public IDepartService getDepartService() {
		return departService;
	}

	public void setDepartService(IDepartService departService) {
		this.departService = departService;
	}


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

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

}
