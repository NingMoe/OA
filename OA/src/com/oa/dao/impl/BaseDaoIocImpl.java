package com.oa.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.oa.dao.IBaseDao;
import com.oa.util.Page;

public class BaseDaoIocImpl<T> implements IBaseDao<T> {

	private SessionFactory sessionFactory;

	/**
	 * 泛型参数的实际类型(从子类传递)
	 */
	private Class<T> entityClass;

	public void init() {
		entityClass = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void save(T instances) {
		try {
			getCurrSession().save(instances);
			getCurrSession().flush();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void saveOrUpdate(T instances) {
		getCurrSession().saveOrUpdate(instances);
		getCurrSession().flush();
	}

	@Override
	public void update(T instances) {
		getCurrSession().update(instances);
		getCurrSession().flush();
	}

	@Override
	public void delete(T instances) {
		getCurrSession().delete(instances);
	}

	@Override
	public Object findById(Class clazz, Serializable id) {
		Object o = getCurrSession().get(clazz, id);
		return o;
	}

	@Override
	public List<T> findAll() {
		init();
		String hql = "FROM " + entityClass.getName();
		return getSessionFactory().getCurrentSession().createQuery(hql).list();
	}

	@Override
	public List<T> findByExample(T t) {
		init();
		return getCurrSession().createCriteria(this.entityClass.getName())
				.add(Example.create(t)).list();
	}

	@Override
	public List<T> findAll(int id, String idName) {
		init();
		String hql = "FROM " + entityClass.getName() + " where " + idName + "="
				+ id;
		return getSessionFactory().getCurrentSession().createQuery(hql).list();
	}

	@Override
	public List findByHql(String hql) {
		Query q = getCurrSession().createQuery(hql);
		List list = q.list();
		return list;
	}

	@Override
	public Integer executeHql(String hql) {
		Query q = getCurrSession().createQuery(hql);
		return q.executeUpdate();
	}

	@Override
	public List findByHql(String hql, Map<String, Object> map, Page page) {
		Query query = getCurrSession().createQuery(hql);
		if (map != null) {
			for (Map.Entry<String, Object> entity : map.entrySet()) {
				query.setParameter(entity.getKey(), entity.getValue());
			}
		}
		if (page != null) {
			page.setTotalRecords(query.list().size());
			query.setMaxResults(page.getPageSize());
			query.setFirstResult(page.getPageStartNum() - 1);
		}
		List list = query.list();
		return list;
	}

	@Override
	public List findBySql(String sql) {
		List list = getCurrSession().createSQLQuery(sql).list();
		return list;
	}

	@Override
	public List findByProperty(Class clazz, String key, String value) {
		List list = new ArrayList();
		Criteria c = getCurrSession().createCriteria(clazz);
		list = c.add(Restrictions.eq(key, value)).list();
		return list;
	}

	@Override
	public Page findPageByHql(String hql, String hqlCount, Page page) {
		// TODO Auto-generated method stub

		page.setList(getCurrSession().createQuery(hql).list());

		page.setPageSize((Integer) getCurrSession().createQuery(hqlCount)
				.list().get(0));

		return page;

	}

	@Override
	public Page findPage(Page page) {
		init();
		long totalRecords = (Long) this.findByHql(
				"select count(*) from " + entityClass.getName()).get(0);

		page.setTotalRecords(totalRecords);

		// 再次设置，避免超过页面有效索引

		page.setCurrentIndex(page.getCurrentIndex());

		Criteria c = getCurrSession().createCriteria(entityClass);

		c.setFirstResult(page.getPageStartNum() - 1);

		c.setMaxResults(page.getPageSize());

		page.setList(c.list());

		return page;
	}

	@Override
	public Page findPage(Page page, Map<String, Object> params) {
		init();

		String hql = "select * from " + entityClass.getName() + "where 1=1";

		long totalRecords = (Long) this.findByHql(
				"select count(*) from " + entityClass.getName()).get(0);

		page.setTotalRecords(totalRecords);

		// 再次设置，避免超过页面有效索引
		page.setCurrentIndex(page.getCurrentIndex());

		// String hql = "select * from " + entityClass + " where 1=1 ";
		// Query q = getCurrSession().createQuery(hql);
		Criteria c = getCurrSession().createCriteria(entityClass);
		c.setFirstResult(page.getPageStartNum() - 1);

		c.setMaxResults(page.getPageSize());

		page.setList(c.list());

		return page;
	}

	@Override
	@Deprecated
	public List findByNamedQuery(Map<String, Object> m) {

		if (m == null)
			return null;

		/*String qName = this.entityClass.getName()+(String) m.get("queryName");*/
		
		Query q = getCurrSession().getNamedQuery((String) m.get("queryName"));

		m.remove("queryName");
		
		Object[] keys = m.keySet().toArray();

		for (Object o : keys) {
			String k = (String)o;
			Object v = m.get(k).getClass().isArray()?((Object[])m.get(k))[0]:m.get(k);
			if(v == null || v.equals("")){
				v = k;
			}
			try {
				q.setParameter(k,v);
			} catch (HibernateException e) {
				System.err.println("异常:无法找到命名参数 --["+k+"]");
			}
		}
		
		System.err.println(q.getQueryString());

		return q.list();
	}

	@Override
	public Session getCurrSession() {
		return getSessionFactory().getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}