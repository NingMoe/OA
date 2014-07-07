package com.oa.dao;

import com.oa.entity.Filetypeinfo;

public interface IFiletypeinfoDao extends IBaseDao<Filetypeinfo> {
	
	public Filetypeinfo findByTypeName(String filetypeName);
	
	public Filetypeinfo findBySuffix(String suffix);
}
