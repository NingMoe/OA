package com.oa.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.oa.dao.IScheduleDao;
import com.oa.dao.IUserDao;
import com.oa.entity.Precontract;
import com.oa.entity.Schedule;
import com.oa.entity.Userinfo;
import com.oa.service.IScheduleService;
import com.oa.util.Page;

public class ScheduleServiceImpl extends BaseServiceImpl<Schedule> implements
		IScheduleService {

	private IScheduleDao scheduleDao;
	private IUserDao userDao;

	@Override
	public List loadDates(String id) {

		Userinfo userinfo = (Userinfo) userDao.findById(Userinfo.class, id);

		List<Precontract> temps = null;
		List dates = null;
		try {
			temps = scheduleDao.loadDates(userinfo);
			dates = new ArrayList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String str = "";
		if (temps != null && !temps.isEmpty()) {
			for (Precontract o : temps) {
				str = o.getSchedule().getBeginTime().toString()
						.substring(0, 10);
				dates.add(str);
			}
		}
		return dates;
	}

	@Override
	public List<Schedule> getAllObject(String userId, Timestamp datetime) {

		return null;
	}

	public IScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	public void setScheduleDao(IScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.service.IScheduleService#assemblePage(com.oa.util.Page,
	 * java.util.Map)
	 */
	@Override
	public void assemblePage(Page basePage, Map<String, Object> param) {
		scheduleDao.assemblePage(basePage, param);
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
