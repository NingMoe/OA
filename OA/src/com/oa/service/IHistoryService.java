package com.oa.service;

import java.util.Set;

import com.oa.entity.Manualsign;
import com.oa.entity.Userinfo;
import com.oa.util.Page;

public interface IHistoryService extends IBaseService<Manualsign> {

	void packPage(Page basePage, Manualsign manualsignStart,
			Manualsign manualsignEnd, Set<Userinfo> userinfos);

}
