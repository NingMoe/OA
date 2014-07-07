package com.oa.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import com.oa.dao.IBaseDao;
import com.oa.service.IBaseService;
import com.oa.util.Page;

public class BaseServiceImpl<T> implements IBaseService<T> {

	protected IBaseDao<T> dao;

	/**
	 * ���Ͳ����ʵ������(�����ഫ��)
	 */
	protected Class<T> entityClass;

	public void init() {
		entityClass = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void delete(T t) {
		dao.delete(t);
	}

	@Override
	public List<T> getAllObject() {
		return dao.findAll();
	}

	/**
	 * �����ID��һ�����
	 */
	@Override
	public List<T> getAllObject(int id, String idName) {
		return dao.findAll(id, idName);
	}

	@Override
	public T getById(Serializable id) {
		init();
		return (T) dao.findById(entityClass, id);
	}

	@Override
	public List getByProperty(Class clazz, String key, String value) {
		// TODO Auto-generated method stub

		return dao.findByProperty(clazz, key, value);
	}

	@Override
	public List getByExample(T t) {
		return getDao().findByExample(t);
	};
	
	@Override
	public List<?> getByNamedQuery(Map<String,Object> m) {
		return getDao().findByNamedQuery(m);
	}
	
	@Override
	public Page getPage(Page page) {
		// TODO Auto-generated method stub
		return getDao().findPage(page);
	}

	@Override
	public void update(T t) {
		dao.update(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		dao.saveOrUpdate(t);
	}

	@Override
	public void save(T t) {
		dao.save(t);
	}

	public void setDao(IBaseDao<T> dao) {
		this.dao = dao;
	}

	public IBaseDao<T> getDao() {
		return dao;
	}

	@Override
	public Page getPageByMap(Page page, Map<String, Object> criteriaMap) {

		return dao.findPage(page, criteriaMap);
	}

	@Override
	public List<T> departs() {
		// TODO Auto-generated method stub
		return null;
	}

}
