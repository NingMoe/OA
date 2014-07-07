package com.oa.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.oa.entity.Precontract;
import com.oa.entity.Schedule;
import com.oa.entity.Userinfo;
import com.oa.util.Page;

public interface IScheduleDao extends IBaseDao<Schedule> {

	List<Precontract> loadDates(Userinfo userinfo);

	void assemblePage(Page basePage, Map<String, Object> param);

	List<Schedule> getObjects(Timestamp datetime, List<Precontract> precontracts,int page,int rows);

}
