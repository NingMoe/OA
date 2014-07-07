package com.oa.dao.impl;


import com.oa.dao.IUserDao;
import com.oa.entity.Fileinfo;
import com.oa.entity.Filetypeinfo;
import com.oa.entity.Userinfo;

public class UserDaoImpl extends BaseDaoIocImpl<Userinfo> implements
		IUserDao {

	@Override
	public void save(Filetypeinfo typeinfo) {
		getCurrSession().save(typeinfo);
		getCurrSession().flush();
	}

	@Override
	public void save(Fileinfo fileinfo) {
		getCurrSession().save(fileinfo);
		getCurrSession().flush();
	}
	
}
