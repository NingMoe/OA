package com.oa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oa.dao.ISysfunDao;
import com.oa.entity.Sysfun;
import com.oa.entity.Userinfo;

public class SysfunDaoImpl extends BaseDaoIocImpl<Sysfun> implements ISysfunDao {

	@Override
	public List<Sysfun> findList(Integer[] roleSysfuns) {
		List<Sysfun> list = null;

		Map<Integer, Object> psysfunMap = new HashMap<Integer, Object>();
		int len = roleSysfuns.length;
		if (roleSysfuns != null && len > 0) {
			list = new ArrayList<Sysfun>();
			for (int j = 0; j < len; j++) {

				Sysfun sysfun = (Sysfun) this.findById(Sysfun.class,
						roleSysfuns[j]);
				list.add(sysfun);
				Sysfun psysfuno = sysfun.getSysfun();

				// 控制有没有父节点 而且在map不存在 代表几个子节共有一个父节点
				if (psysfuno != null
						&& !psysfunMap.containsKey(psysfuno.getNodeId())) {
					psysfunMap.put(psysfuno.getNodeId(), psysfuno);
				}
			}
		}
		for (Integer id : psysfunMap.keySet()) {
			list.add((Sysfun) psysfunMap.get(id));
		}
		return list;
	}

	@Override
	public List<Sysfun> getAll(Userinfo userinfo) {
		StringBuffer hql = new StringBuffer(
				"SELECT distinct sysfun.nodeId,sysfun FROM Userinfo us");
		hql.append(" join us.roleinfos as roleinfos");
		hql.append(" join roleinfos.rolerights as rolerights");
		hql.append(" join rolerights.sysfun as sysfun");

		Map<String, Object> params = null;
		if (userinfo != null) {
			params = new HashMap<String, Object>();
			hql.append(" where us.userId=:userid");
			params.put("userid", userinfo.getUserId());
		} else {
			params = null;
		}
		hql.append(" order by sysfun.nodeId");

		List temps = this.findByHql(hql.toString(), params, null);
		return temps;
	}
}
