package com.oa.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.oa.dao.IRolerightDao;
import com.oa.entity.Roleinfo;
import com.oa.entity.Roleright;
import com.oa.entity.Sysfun;

public class RolerightDaoImpl extends BaseDaoIocImpl<Roleright> implements
		IRolerightDao {

	@Override
	public void delete(Roleright instances) {

		try {
			Session session = getSessionFactory().getCurrentSession();
			session.delete(instances);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Roleright> deleteHql(String string, Map<String, Object> map,
			Object object) {
		Session session = getSessionFactory().getCurrentSession();
		Query q = session.createQuery(string);
		q.executeUpdate();
		return null;
	}

	@Override
	public void saveSet(Set rolerights) {
		try {
			Session session = getSessionFactory().getCurrentSession();
			for (Object object : rolerights) {
				session.save(object);
			}
			session.flush();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Set newRolerights(List<Sysfun> sysfuns, Roleinfo roleinfo) {
		Set rolerights = null;

		if (sysfuns != null && roleinfo != null && !sysfuns.isEmpty()) {
			rolerights=new HashSet<Roleright>();
			for (int i = 0; i < sysfuns.size(); i++) {

				Roleright roleright = new Roleright();
				roleright.setRoleinfo(roleinfo);
				roleright.setSysfun(sysfuns.get(i));
				rolerights.add(roleright);
			}
		}

		return rolerights;
	}

}
