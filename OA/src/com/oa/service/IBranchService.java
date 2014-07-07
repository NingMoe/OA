package com.oa.service;

import java.util.Set;

import com.oa.entity.Branchinfo;
import com.oa.entity.Userinfo;
import com.oa.util.Message;

public interface IBranchService extends IBaseService<Branchinfo> {

	/**
	 * 获取机构下的所有人员
	 * @param branchinfo
	 * @return
	 */
	Set<Userinfo> getBranchsFieldUser(Branchinfo branchinfo);
	
	/**
	 * 删除机构-(级联)
	 * @param bid 机构ID
	 * @param uid 当前登录用户,用以判断权限
	 * @param msg 操作消息传递对象
	 * @return Struts跳转方式
	 */
	public String delete(Integer bid,Userinfo currUser,Message msg);

}
