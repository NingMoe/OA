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
	 * ��ѯһ�ű��е����м�¼
	 * 
	 * @return
	 */
	public List<T> findAll();

	public List<T> findAll(int id, String idName);
	
	public List<T> findByExample(T t);

	/**
	 * ����Hql��ѯ����
	 * 
	 * @param hql
	 * @return
	 */
	public List findByHql(String hql);

	/**
	 * ����Hql��ѯ����
	 * 
	 * @param hql
	 * @return
	 */
	public List findByHql(String hql, Map<String, Object> map,Page page);

	/**
	 * �����������Ʋ�ѯ����
	 * 
	 * @param ʵ����
	 * @param ������
	 * @param ����ֵ
	 * @return list
	 */
	public List findByProperty(Class clazz, String key, String value);

	/**
	 * ʵ�ַ�ҳ����,���һҳ����
	 * 
	 * @param ��ѯ��ǰҳ���ݵ�hql
	 * @param ��ѯ�ܼ�¼����hql
	 * @param page����
	 * @return ��װ����������ҳ
	 */
	public Page findPageByHql(String hql, String hqlCount, Page page);

	/**
	 * ʵ�ַ�ҳ����,���һҳ����
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
