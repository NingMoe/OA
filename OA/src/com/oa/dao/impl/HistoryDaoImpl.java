package com.oa.dao.impl;

import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.oa.dao.IHistoryDao;
import com.oa.entity.Manualsign;
import com.oa.entity.Userinfo;
import com.oa.util.Page;

public class HistoryDaoImpl extends BaseDaoIocImpl<Manualsign> implements
		IHistoryDao {

	@Override
	public void setBasePageTotalRecords(Page basePage,
			Manualsign manualsignStart, Manualsign manualsignEnd,
			Set<Userinfo> userinfos) {
		try {
			Criteria criteria = getCurrSession().createCriteria(
					Manualsign.class);
			if (userinfos != null && !userinfos.isEmpty()) {
				Criterion[] cs1 = new Criterion[userinfos.size()];
				int i = 0;
				for (Userinfo userinfo : userinfos) {
					Criterion ct1 = Restrictions.eq("userinfo",
							userinfo);

					cs1[i] = ct1;
					i++;
				}
				i = 0;
				Criterion cor1 = Restrictions.or(cs1);
				criteria.add(cor1);

			}
			if (manualsignStart != null && manualsignEnd != null) {
				criteria.add(Restrictions.between("signTime",
						manualsignStart.getSignTime(),
						manualsignEnd.getSignTime()));
			}
			basePage.setTotalRecords(criteria.list().size());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setPageList(Page basePage, Manualsign manualsignStart,
			Manualsign manualsignEnd, Set<Userinfo> userinfos) {
		try {
			Criteria criteria = getCurrSession().createCriteria(
					Manualsign.class);
			if (userinfos != null && !userinfos.isEmpty()) {
				Criterion[] cs1 = new Criterion[userinfos.size()];
				int i = 0;
				for (Userinfo userinfo : userinfos) {
					Criterion ct1 = Restrictions.eq("userinfo",
							userinfo);

					cs1[i] = ct1;
					i++;
				}
				i = 0;
				Criterion cor1 = Restrictions.or(cs1);
				criteria.add(cor1);
			}
			if (manualsignStart != null && manualsignEnd != null) {
				criteria.add(Restrictions.between("signTime",
						manualsignStart.getSignTime(),
						manualsignEnd.getSignTime()));
			}
			criteria.setMaxResults(basePage.getPageSize());
			criteria.setFirstResult(basePage.getPageStartNum()-1);

			basePage.setList(criteria.list());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
