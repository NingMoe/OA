package com.oa.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.oa.dao.IScheduleDao;
import com.oa.entity.Precontract;
import com.oa.entity.Schedule;
import com.oa.entity.Userinfo;
import com.oa.util.Page;

public class ScheduleDaoImpl extends BaseDaoIocImpl<Schedule> implements
		IScheduleDao {

	@Override
	public List<Precontract> loadDates(Userinfo userinfo) {
		List<Precontract> precontractList = null;
		Set<Precontract> precontracts = userinfo.getPrecontracts();
		// System.out.println(userinfo.getUserId()+userinfo.getUserName());
		if (!precontracts.isEmpty() && precontracts != null) {
			precontractList = new ArrayList<Precontract>();
			for (Precontract precontract : precontracts) {
				precontractList.add(precontract);
			}
		}
		return precontractList;
	}

	@Override
	public void assemblePage(Page basePage, Map<String, Object> param) {
		// 给basePage 装配 totalRecords，list
		String hql = "";
		List list = null;

		if (param == null || param.isEmpty()) {
			hql = "FROM Schedule";
		} else {
			hql = "FROM Schedule where beginTime>:beginTime";

		}
		Query query = getCurrSession().createQuery(hql);
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			if ("beginTime".equals(entry.getKey())) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			System.out.println("key= " + entry.getKey() + " and value= "
					+ entry.getValue());
		}
		list = query.list();
		Long totalRecords = 0L;
		try {
			if (list != null || !list.isEmpty()) {
				totalRecords = (long) list.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		basePage.setTotalRecords(totalRecords);
		basePage.setList(list);
	}

	@Override
	public List<Schedule> getObjects(Timestamp datetime,
			List<Precontract> precontracts, int page, int rows) {
		List<Schedule> schedules = null;
		Criteria criteria = getCurrSession().createCriteria(Schedule.class);
		Criterion[] cs = new Criterion[precontracts.size()];
		for (int i = 0, size = precontracts.size(); i < size; i++) {
			Criterion rs = Restrictions.eq("scheduleId", precontracts.get(i)
					.getSchedule().getScheduleId());
			cs[i] = rs;

		}
		Criterion cor = Restrictions.or(cs);
		criteria.add(cor);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义格式，不显示毫秒
		String date = df.format(datetime);
		String data1 = date.substring(0, 10) + " 23:59:59";

		Timestamp datetime1 = null;
		try {
			datetime1 = Timestamp.valueOf(data1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		criteria.add(Restrictions.between("beginTime", datetime, datetime1));
		criteria.setMaxResults(rows);
		criteria.setFirstResult(page);
		schedules = criteria.list();
		return schedules;
	}
}
