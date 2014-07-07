package com.oa.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.oa.entity.Precontract;
import com.oa.entity.Schedule;
import com.oa.entity.Userinfo;
import com.oa.util.Page;

public interface IPrecontractService extends IBaseService<Precontract> {

	List<Schedule> getAllObject(String userId, Timestamp datetime, int page,
			int rows);

	void assemblePage(Page basePage, Map<String, Object> param);

	void saveOrUpdate(Schedule schedule, List<Userinfo> userinfos);

	void deleteWhere(Object object);


}
