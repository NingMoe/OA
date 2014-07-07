package com.oa.action.impl;

import java.util.HashMap;
import java.util.Map;
import com.oa.action.IBaseAction;

/**
 * ��Action��ı�д,��Ŀ����Ϊ�˼���Action��Ŀ���������,ʵ����easyui����µ�ҳ������ý���.
 * 
 * @author �̱���
 * @param <T>
 *            ��Ӧ��POJO
 */
public abstract class Action4easyui<T> extends BaseAction2<T> implements
		IBaseAction {

	private static final long serialVersionUID = 1L;
	/**
	 * ҳ���С
	 */
	private int rows;
	/**
	 * ��ǰҳ�������
	 */
	private int page;
	/**
	 * ���ظ�ҳ������
	 */
	private Map<String, Object> datas;
	/**
	 * �Ƿ��ύ�¼�¼
	 */
	private boolean isNewRecord;
	/**
	 * ҳ��ļ�¼�е�ID
	 */
	private String id;

	// /////////////////////////////////// Method
	// ////////////////////////////////////////////

	@Override
	public String showPage() {

		super.getBasePage().setCurrentIndex(page);
		super.getBasePage().setPageSize(rows);
		super.showPage();

		getDatas().put("total", super.getBasePage().getTotalRecords());
		getDatas().put("rows", super.getBasePage().getList());

		return SUCCESS;
	}

	@Override
	public String showAll() {
		super.showAll();
		getDatas().clear();
		getDatas().put("baseList", getBaseList());
		return SUCCESS;
	}
	
	@Override
	public abstract String show();
	
	// //////////////////////////////// getter&setter
	// ////////////////////////////////////////////
	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}

	public Map<String, Object> getDatas() {
		if (datas == null) {
			datas = new HashMap<String, Object>();
		}
		return datas;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	public boolean getIsNewRecord() {
		return isNewRecord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
