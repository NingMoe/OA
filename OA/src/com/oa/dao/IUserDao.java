package com.oa.dao;

import com.oa.entity.Fileinfo;
import com.oa.entity.Filetypeinfo;
import com.oa.entity.Userinfo;

public interface IUserDao extends IBaseDao<Userinfo> {

	public void save(Filetypeinfo typeinfo);
	
	public void save(Fileinfo fileinfo);
}
