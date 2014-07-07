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
	 * �ع����෽��,���²��Ÿ����˵�departId
	 */
	@Override
	public void saveOrUpdate(Departinfo t);

	public Set<Userinfo> getDeparts(Departinfo departinfo,Userinfo userinfo);
	
	/**
	 * ɾ������(����ɾ���û�)
	 * @param departId ����ID
	 * @param currUser ��ǰ��¼�û�,���Լ��Ȩ��
	 * @param msg ����֮�����Ϣ���ݶ���
	 * @return Struts��ת��ʽ
	 */
	public String delete(Integer departId,Userinfo currUser,Message msg);
}
