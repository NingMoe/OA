package com.oa.service;


import java.util.List;
import java.util.Set;

import com.oa.entity.Departinfo;
import com.oa.entity.Userinfo;
import com.oa.util.Message;
import com.oa.util.Tree;

public interface IDepartService extends IBaseService<Departinfo> {

	public Object getAllBranch();
	
	public Departinfo onSaveOrUpdate(Departinfo depart);
	public boolean prop_val_exists(String prop,Object val);
	
	public List<Tree> getBranchStaff();
	
	/**
	 * 重构父类方法,更新部门负责人的departId
	 */
	@Override
	public void saveOrUpdate(Departinfo t);

	public Set<Userinfo> getDeparts(Departinfo departinfo,Userinfo userinfo);
	
	/**
	 * 删除部门(级联删除用户)
	 * @param departId 部门ID
	 * @param currUser 当前登录用户,用以检查权限
	 * @param msg 对象之间的消息传递对象
	 * @return Struts跳转方式
	 */
	public String delete(Integer departId,Userinfo currUser,Message msg);
}
