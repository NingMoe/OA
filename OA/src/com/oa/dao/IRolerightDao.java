package com.oa.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oa.entity.Roleinfo;
import com.oa.entity.Roleright;
import com.oa.entity.Sysfun;

public interface IRolerightDao extends IBaseDao<Roleright> {

	List<Roleright> deleteHql(String string, Map<String, Object> map,
			Object object);

	void saveSet(Set rolerights);

	Set newRolerights(List<Sysfun> sysfuns, Roleinfo roleinfo);

}
