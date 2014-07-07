package com.oa.service;

import java.util.Map;

import com.oa.entity.Operatelog;

public interface IOperateService extends IBaseService<Operatelog> {

	public Map searchBytime(String operateTime, String nextTime);

	public void saveLog(Operatelog operatelog);
}
