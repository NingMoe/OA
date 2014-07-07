package com.oa.service.impl;

import java.util.List;

import com.oa.dao.IWorkTimeDao;
import com.oa.entity.Branchinfo;
import com.oa.entity.Departinfo;
import com.oa.entity.Manualsign;
import com.oa.entity.Userinfo;
import com.oa.service.IWorkTimeService;
import com.oa.util.Page;

public class WorkTimeServiceImpl extends BaseServiceImpl<Manualsign> implements
		IWorkTimeService {

	@Override
	public void pageShow(Page page, Manualsign manualsignStart,
			Manualsign manualsignEnd, Branchinfo branchinfo,
			Departinfo departinfo) {

		IWorkTimeDao workTimeDao = (IWorkTimeDao) getDao();
		List<Userinfo> userinfos=null;
//		workTimeDao.setPageTotal(page, userinfos);
//		//统计每个用户的值，且把page中list 给encapsulation
//		workTimeDao.setPageList(page,userinfos);
	}

}
