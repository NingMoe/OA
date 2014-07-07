package com.oa.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.oa.action.IBaseAction;
import com.oa.service.IBaseService;
import com.oa.util.Page;

public abstract class BaseAction2<T> extends BaseAction implements IBaseAction {
	private static final long serialVersionUID = 1L;

	/**
	 * POJO实例
	 */
	private T pojo;
	/**
	 * 基础服务类接口
	 */
	private IBaseService<T> baseService;

	/**
	 * 对象集合(action与页面交互的数据容器)
	 */
	private List<?> baseList;

	/**
	 * 数据页(封装了一页数据,(action与页面交互的数据容器))
	 */
	private Page basePage;

	protected Map<String, Object> criteriaMap = new HashMap<String, Object>();

	// ////////////////////////// method ///////////////////////////////
	@Override
	public String add() {
		getBaseService().save(pojo);
		return SUCCESS;
	}

	@Override
	public String remove() {
		// TODO Auto-generated method stub
		getBaseService().delete(pojo);
		return SUCCESS;
	}

	@Override
	public String update() {
		getBaseService().update(pojo);
		return SUCCESS;
	}

	@Override
	public String showAll() {
		setBaseList(getBaseService().getAllObject());
		return SUCCESS;
	}

	@Override
	public abstract String show();

	@Override
	public String showPage() {
		setBasePage(getBaseService().getPage(getBasePage()));
		return SUCCESS;

	}

	/**
	 * 实现接口中的方法 须要一个Map
	 */
	@Override
	public String showPageByMap() {
		return null;
	}

	@Override
	public String addOrUpdate() {
		getBaseService().saveOrUpdate(getPojo());
		return SUCCESS;
	}

	// ///////////////////////// getter&setter ///////////////////////////////

	public void setBaseList(List<?> baseList) {
		this.baseList = baseList;
	}
	

	public List<?> getBaseList() {
		return baseList;
	}

	public void setBasePage(Page basePage) {
		this.basePage = basePage;
	}

	public Page getBasePage() {
		if (basePage == null) {
			basePage = new Page();
		}
		return basePage;
	}

	public T getPojo() {
		return pojo;
	}

	public void setPojo(T pojo) {
		this.pojo = pojo;
	}

	public IBaseService<T> getBaseService() {
		return baseService;
	}

	public void setBaseService(IBaseService<T> baseService) {
		this.baseService = baseService;
	}

}
