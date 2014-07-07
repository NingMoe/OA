package com.oa.service;

import java.util.Set;

import com.oa.entity.Branchinfo;
import com.oa.entity.Userinfo;
import com.oa.util.Message;

public interface IBranchService extends IBaseService<Branchinfo> {

	/**
	 * ��ȡ�����µ�������Ա
	 * @param branchinfo
	 * @return
	 */
	Set<Userinfo> getBranchsFieldUser(Branchinfo branchinfo);
	
	/**
	 * ɾ������-(����)
	 * @param bid ����ID
	 * @param uid ��ǰ��¼�û�,�����ж�Ȩ��
	 * @param msg ������Ϣ���ݶ���
	 * @return Struts��ת��ʽ
	 */
	public String delete(Integer bid,Userinfo currUser,Message msg);

}
