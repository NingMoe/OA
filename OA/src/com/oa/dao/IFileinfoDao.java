package com.oa.dao;

import java.util.List;
import java.util.Map;

import com.oa.entity.Fileinfo;

public interface IFileinfoDao extends IBaseDao<Fileinfo>{

	public Fileinfo findAvatar(String userId);
	
	public List<Fileinfo> findByCriteria(Map<String,Object> m);
}
