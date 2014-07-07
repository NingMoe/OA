package com.oa.action;


public interface IBaseAction {

	public String add();
	

	public String remove();

	public String update();

	public String showAll();

	public String show();

	public String showPage();

	/**
	 * 閺夆�娆㈤弻銉嚄 鐎涙劗琚獳CTION娑擃厼濮炴稉锟介嚋Map
	 * 
	 * @return
	 */
	public String showPageByMap();
	
	public String addOrUpdate();
	
}
