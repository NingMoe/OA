package com.oa.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.oa.entity.Schedule;
import com.oa.util.Page;

public interface IScheduleService extends IBaseService<Schedule> {

	List loadDates(String id);

	/**
	 * 装配page
	 * 
	 * @param basePage
	 * @param param
	 *            要装配的条件
	 */
	void assemblePage(Page basePage, Map<String, Object> param);

	/**
	 * 查询所有日程 要求 用户id,日期
	 * @param userId
	 * @param datetime
	 * @return
	 */
	List<Schedule>  getAllObject(String userId, Timestamp datetime);
}
