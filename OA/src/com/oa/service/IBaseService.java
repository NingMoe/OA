package com.oa.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.oa.util.Page;

public interface IBaseService<T> {

	public void save(T t);

	public void delete(T t);

	public void update(T t);
	
	public void saveOrUpdate(T t);

	public List<T> departs();

	public T getById(Serializable id);
	
	public List getByExample(T t);

	public List<T> getAllObject();

	public List<T> getAllObject(int id, String idName);

	public List getByProperty(Class clazz, String key, String value);

	public Page getPage(Page page);

	public Page getPageByMap(Page page, Map<String, Object> criteriaMap);
	
	public List<?> getByNamedQuery(Map<String,Object> m);
}
