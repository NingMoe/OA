package com.oa.dao;

import com.oa.entity.Operatelog;



public interface IOperateDao extends IBaseDao<Operatelog>{

/*	public void deleteAll();*/
	
	@Override
	public void save(Operatelog instances);
	
}
