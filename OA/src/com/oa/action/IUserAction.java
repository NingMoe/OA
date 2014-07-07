package com.oa.action;

import java.util.List;

import com.oa.service.IUserService;

public interface IUserAction extends IBaseAction{
	
	public String showAllDepart();
	
	public String showAllRole();
	
	public String showAllState();
	
	public IUserService getRealityService();
	
	/**
	 * 将从后台获取的集合数据装入到此Action类属性
	 * @param clazz POJO类型,据此查询集合
	 * @param list 存入的集合属性(必须初始化)
	 */
	public void setActionProp(Class<?> clazz,List list);
	
	public String logout();
	
	public String modifyPwd();
	
	/**
	 * 验证用户名是否存在
	 * @return
	 */
	public String nameExists();
	
}
