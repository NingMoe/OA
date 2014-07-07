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

		// ��ѯ�� totalRecords ��������¶�������¼
		historyDao.setBasePageTotalRecords(basePage, manualsignStart,
				manualsignEnd, userinfos);

		// �õ������ѯ�µ�ĳҳ�ļ�¼ x����¼
		historyDao.setPageList(basePage, manualsignStart, manualsignEnd,
				userinfos);

	}
}
