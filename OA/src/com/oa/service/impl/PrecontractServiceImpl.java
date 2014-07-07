package com.oa.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.oa.dao.IPrecontractDao;
import com.oa.dao.IScheduleDao;
import com.oa.entity.Precontract;
import com.oa.entity.Schedule;
import com.oa.entity.Userinfo;
import com.oa.service.IPrecontractService;
import com.oa.util.Page;

public class PrecontractServiceImpl extends BaseServiceImpl<Precontract>
		implements IPrecontractService {

	private IPrecontractDao precontractDao;
	private IScheduleDao scheduleDao;

	/*
	 * (non-Javadoc) 查询当前登录用户，当前日期的日程
	 * 
	 * @see com.oa.service.IPrecontractService#getAllObject(java.lang.String,
	 * java.sql.Timestamp)
	 */
	@Override
	public List<Schedule> getAllObject(String userId, Timestamp datetime,
			int page, int rows) {
		List<Precontract> precontracts = null;
		List<Schedule> schedules = null;

		precontracts = precontractDao.getAllObject(userId);
		if (precontracts != null && !precontracts.isEmpty()) {

			schedules = scheduleDao.getObjects(datetime, precontracts,
					(page - 1), rows);
		} else {
			schedules = null;
		}
		return schedules;
	}

	/**
	 * 对page 进行封装 通过条件param 得到 日程
	 */
	@Override
	public void assemblePage(Page basePage, Map<String, Object> param) {

		try {
			StringBuffer hql = new StringBuffer();
			/*
			 * 通过不能参加生成不HQL语句
			 */
			hql.append("SELECT distinct sch.scheduleId,sch FROM Branchinfo bra");
			hql.append(" right join bra.departinfos dep");
			hql.append(" right join dep.employees user");
			hql.append(" right join user.precontracts pre");
			hql.append(" right join pre.schedule sch");
			// 所有条件全部存在
			Iterator iterator1 = null;
			if (param != null && !param.isEmpty()) {
				hql.append(" where 1=1");
			}
			if (param.containsKey("beginTime")) {
				Calendar c = Calendar.getInstance();
				Timestamp ts = (Timestamp) param.get("beginTime");
				Date date = ts;
				c.setTime(date);
				int day = c.get(Calendar.DATE);
				c.set(Calendar.DATE, day + 1);
				Timestamp endTime = new Timestamp(c.getTime().getTime());
				param.put("endTime", endTime);
				hql.append(" and sch.beginTime between :beginTime and :endTime");
			}
			if (param.containsKey("userName")) {
				hql.append(" and user.userName=:userName");
			}
			if (param.containsKey("departId")) {
				hql.append(" and dep.departId=:departId");
			}
			if (param.containsKey("branchId")) {
				hql.append(" and bra.branchId=:branchId ");
			}
			int len = param.size();
			List<Schedule> schedules = new ArrayList<Schedule>();
			if (param == null || len == 0) {
				hql = new StringBuffer("FROM Schedule sch");
				iterator1 = precontractDao.findByHql(hql.toString(), null,
						basePage).iterator();
				if (iterator1 != null) {
					// 把查出的数据放到schedules
					while (iterator1.hasNext()) {
						Object o = iterator1.next();
						Schedule schedule = (Schedule) o;
						schedules.add(schedule);
					}
				}
			}
			if (param != null && len > 0) {
				iterator1 = precontractDao.findByHql(hql.toString(), param,
						basePage).iterator();
				if (iterator1 != null) {
					// 把查出的数据放到schedules
					while (iterator1.hasNext()) {
						Object[] o = (Object[]) iterator1.next();
						Schedule schedule = (Schedule) o[1];
						schedules.add(schedule);
					}
				}
			}

			precontractDao.assemblePage(basePage, schedules);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 保存修改
	@Override
	public void saveOrUpdate(Schedule schedule, List<Userinfo> userinfos) {
		List<Precontract> precontracts = new ArrayList<Precontract>();

		for (Userinfo userinfo : userinfos) {
			Precontract precontract = new Precontract();
			precontract.setSchedule(schedule);
			precontract.setUserinfo(userinfo);
			precontracts.add(precontract);
		}

		precontractDao.saveOrUpdate(precontracts);

	}

	@Override
	public void deleteWhere(Object object) {

		Schedule schedule =(Schedule) object;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("scheduleId", schedule.getScheduleId());
		precontractDao.deleteHql("delete Precontract p where p.schedule.scheduleId=:scheduleId",map);
	}

	public IPrecontractDao getPrecontractDao() {
		return precontractDao;
	}

	public void setPrecontractDao(IPrecontractDao precontractDao) {
		this.precontractDao = precontractDao;
	}

	public IScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	public void setScheduleDao(IScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

}
