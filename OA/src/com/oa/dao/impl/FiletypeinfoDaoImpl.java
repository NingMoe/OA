package com.oa.dao.impl;

import java.util.List;

import com.oa.dao.IFiletypeinfoDao;
import com.oa.entity.Filetypeinfo;

public class FiletypeinfoDaoImpl extends BaseDaoIocImpl<Filetypeinfo> implements
		IFiletypeinfoDao {

	@Override
	public Filetypeinfo findByTypeName(String filetypeName) {

		List list = findByProperty(Filetypeinfo.class, "fileTypeName",
				filetypeName);
		if(list != null && !list.isEmpty()){
			return (Filetypeinfo) list.get(0);
		}
		return null;
	}
	
	@Override
	public Filetypeinfo findBySuffix(String suffix) {
		List list = findByProperty(Filetypeinfo.class, "fileTypeSuffix",
				suffix);
		if(list != null && !list.isEmpty()){
			return (Filetypeinfo) list.get(0);
		}
		return null;
	}
	
}
