package com.oa.dao;

import java.util.List;
import com.oa.entity.Sysfun;
import com.oa.entity.Userinfo;

public interface ISysfunDao extends IBaseDao<Sysfun> {

	List<Sysfun> findList(Integer[] roleSysfuns);

	List<Sysfun> getAll(Userinfo userinfo);


}
