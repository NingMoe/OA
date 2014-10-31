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
	 * POJOʵ��
	 */
	private T pojo;
	/**
	 * ����������ӿ�
	 */
	private IBaseService<T> baseService;

	/**
	 * ���󼯺�(action��ҳ�潻������������)
	 */
	private List<?> baseList;

	/**
	 * ����ҳ(��װ��һҳ����,(action��ҳ�潻������������))
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
	 * ʵ�ֽӿ��еķ��� ��Ҫһ��Map
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
