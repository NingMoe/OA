package com.oa.service.impl;

import java.util.Set;

import com.oa.dao.IHistoryDao;
import com.oa.entity.Manualsign;
import com.oa.entity.Userinfo;
import com.oa.service.IHistoryService;
import com.oa.util.Page;

public class HistoryServiceImpl extends BaseServiceImpl<Manualsign> implements
		IHistoryService {

	@Override
	public void packPage(Page basePage, Manualsign manualsignStart,
			Manualsign manualsignEnd, Set<Userinfo> userinfos) {
		IHistoryDao historyDao = (IHistoryDao) getDao();

		// 查询出 totalRecords 这个条件下多少条记录
		historyDao.setBasePageTotalRecords(basePage, manualsignStart,
				manualsignEnd, userinfos);

		// 得到这个查询下的某页的记录 x条记录
		historyDao.setPageList(basePage, manualsignStart, manualsignEnd,
				userinfos);

	}
}
