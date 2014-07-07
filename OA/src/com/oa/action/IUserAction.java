package com.oa.action;

import java.util.List;

import com.oa.service.IUserService;

public interface IUserAction extends IBaseAction{
	
	public String showAllDepart();
	
	public String showAllRole();
	
	public String showAllState();
	
	public IUserService getRealityService();
	
	/**
	 * ���Ӻ�̨��ȡ�ļ�������װ�뵽��Action������
	 * @param clazz POJO����,�ݴ˲�ѯ����
	 * @param list ����ļ�������(�����ʼ��)
	 */
	public void setActionProp(Class<?> clazz,List list);
	
	public String logout();
	
	public String modifyPwd();
	
	/**
	 * ��֤�û����Ƿ����
	 * @return
	 */
	public String nameExists();
	
}
