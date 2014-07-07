package com.oa.dao.impl;

import com.oa.dao.IOperateDao;
import com.oa.entity.Operatelog;

public class OperateDaoImpl extends BaseDaoIocImpl<Operatelog> implements
		IOperateDao {

	/*
	 * public void deleteAll() { // TODO Auto-generated method stub String
	 * hql="delete from "+Loginlog.class;
	 * 
	 * }
	 */
	@Override
	public void save(Operatelog instances) {
		try {
			getCurrSession().save(instances);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

}
