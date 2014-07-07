package com.oa.dao;

import java.util.List;
import java.util.Map;

import com.oa.entity.Precontract;
import com.oa.entity.Schedule;
import com.oa.util.Page;

public interface IPrecontractDao extends IBaseDao<Precontract> {

	List<Precontract> getAllObject(String userId);

	void assemblePage(Page basePage,  List<Schedule> schedules);

	void saveOrUpdate(List<Precontract> precontracts);

	void deleteHql(String string, Map<String, Object> map);

}
