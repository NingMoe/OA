package com.oa.service;

import com.oa.entity.Branchinfo;
import com.oa.entity.Departinfo;
import com.oa.entity.Manualsign;
import com.oa.util.Page;

public interface IWorkTimeService extends IBaseService<Manualsign> {

	void pageShow(Page page, Manualsign manualsignStart,
			Manualsign manualsignEnd, Branchinfo branchinfo,
			Departinfo departinfo);


}
