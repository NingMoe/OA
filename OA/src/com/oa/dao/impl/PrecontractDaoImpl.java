package com.oa.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import com.oa.dao.IPrecontractDao;
import com.oa.entity.Precontract;
import com.oa.entity.Schedule;
import com.oa.util.Page;

public class PrecontractDaoImpl extends BaseDaoIocImpl<Precontract> implements
		IPrecontractDao {

	@Override
	public List<Precontract> getAllObject(String userId) {
		String hql = "FROM Precontract where userinfo.userId=:userid";
		Query query = getCurrSession().createQuery(hql);
		query.setString("userid", userId);
		List precontracts = query.list();
		return precontracts;
	}

	// 保存对象
	@Override
	public void saveOrUpdate(List<Precontract> precontracts) {
		try {
			for (int i = 0; i < precontracts.size(); i++) {
				getCurrSession().save(precontracts.get(i));
				if (i % 5 == 0) {
					getCurrSession().flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteHql(String string, Map<String, Object> map) {
		try {
			Query query = getCurrSession().createQuery(string);
			if (map != null) {
				for (Map.Entry<String, Object> entity : map.entrySet()) {
					query.setParameter(entity.getKey(), entity.getValue());
				}
			}
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void assemblePage(Page basePage, List<Schedule> schedules) {
		// 给basePage 装配 totalRecords，list
		// String hql = "";
		// List<Schedule> schedules = null;
		// Criteria criteria = getCurrSession().createCriteria(Schedule.class);
		// Criterion[] cs = new Criterion[precontracts.size()];
		// for (int i = 0, size = precontracts.size(); i < size; i++) {
		// Criterion rs = Restrictions.eq("scheduleId", precontracts.get(i)
		// .getSchedule().getScheduleId());
		// cs[i] = rs;
		//
		// }
		// Criterion cor = Restrictions.or(cs);
		// criteria.add(cor);
		// Long totalRecords = 0l;
		// try {
		// if (schedules != null && !schedules.isEmpty()) {
		// totalRecords = (long) basePage.getTotalRecords();
		// // 、、、、、、、、、、、、、、 这里有问题
		// // totalRecords = (long) schedules.size();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		basePage.setList(schedules);
	}
}
