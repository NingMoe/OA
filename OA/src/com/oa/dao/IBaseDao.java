package com.oa.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.oa.util.Page;

public interface IBaseDao<T> {

	public void save(T instances);

	public void update(T instances);

	public void delete(T instances);

	public void saveOrUpdate(T instances);

	public Object findById(Class clazz, Serializable id);

	/**
	 * 查询一张表中的所有记录
	 * 
	 * @return
	 */
	public List<T> findAll();

	public List<T> findAll(int id, String idName);
	
	public List<T> findByExample(T t);

	/**
	 * 根据Hql查询数据
	 * 
	 * @param hql
	 * @return
	 */
	public List findByHql(String hql);

	/**
	 * 根据Hql查询数据
	 * 
	 * @param hql
	 * @return
	 */
	public List findByHql(String hql, Map<String, Object> map,Page page);

	/**
	 * 根据属性名称查询数据
	 * 
	 * @param 实体类
	 * @param 属性名
	 * @param 属性值
	 * @return list
	 */
	public List findByProperty(Class clazz, String key, String value);

	/**
	 * 实现分页功能,获得一页数据
	 * 
	 * @param 查询当前页数据的hql
	 * @param 查询总记录数的hql
	 * @param page对象
	 * @return 封装完整的数据页
	 */
	public Page findPageByHql(String hql, String hqlCount, Page page);

	/**
	 * 实现分页功能,获得一页数据
	 * 
	 * @param page
	 * @return
	 */
	public Page findPage(Page page);

	public Page findPage(Page page, Map<String, Object> criteriaMap);

	public List findBySql(String sql);
	
	public List findByNamedQuery(Map<String,Object> m);
	
	public Integer executeHql(String hql);
	
	public Session getCurrSession();
}
