package com.oa.dao;

import java.util.Set;

import com.oa.entity.Manualsign;
import com.oa.entity.Userinfo;
import com.oa.util.Page;

public interface IHistoryDao extends IBaseDao<Manualsign> {

	void setBasePageTotalRecords(Page basePage, Manualsign manualsignStart,
			Manualsign manualsignEnd, Set<Userinfo> userinfos);

	void setPageList(Page basePage, Manualsign manualsignStart,
			Manualsign manualsignEnd, Set<Userinfo> userinfos);
	
	

}
