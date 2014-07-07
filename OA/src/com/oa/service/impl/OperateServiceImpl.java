package com.oa.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.oa.dao.IOperateDao;
import com.oa.entity.Operatelog;
import com.oa.service.IOperateService;

public class OperateServiceImpl extends BaseServiceImpl<Operatelog> implements
		IOperateService {

	@Override
	public Map searchBytime(String operateTime, String nextTime) {
		// TODO Auto-generated method stub
		String hql = "from Operatelog where operateTime BETWEEN " + "'"
				+ operateTime + "' AND '" + nextTime + "'";
		List<Operatelog> lists = getDao().findByHql(hql);

		Map datas = new HashedMap();

		datas.put("total", lists.size());

		datas.put("rows", lists);

		return datas;
	}

	@Override
	public void saveLog(Operatelog operatelog) {
		try {
			IOperateDao dao= (IOperateDao) getDao();
			dao.save(operatelog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
