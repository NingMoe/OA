package com.oa.action.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oa.action.IPrecontractAction;
import com.oa.entity.Precontract;
import com.oa.entity.Schedule;
import com.oa.entity.Userinfo;
import com.oa.service.IPrecontractService;

public class PrecontractAction extends Action4easyui<Precontract> implements
		IPrecontractAction {
	private IPrecontractService precontractService;

	private int page;
	private int rows;

	// 条件查询 当前日期

	// 查询条件
	private String datetime;

	private String name;
	private int bumen;
	private int jiguo;

	private int bumen_dialog;
	private int jiguo_dialog;

	// 条件查询的map
	private Map<String, Object> param;

	/**
	 * 用来实现条件查询
	 * 
	 * @param param
	 * @return
	 */
	@Override
	public String showPage() {

		try {
			name = new String(name.getBytes("utf-8"), "utf-8");
			// System.out.println("datetime:" + getDatetime() + " name:" + name
			// + " bumen:" + bumen + " jiguo:" + jiguo);
			super.getBasePage().setCurrentIndex(this.getPage());
			super.getBasePage().setPageSize(this.getRows());
			// 装载Page,MAP
			setParam(new HashMap<String, Object>());
			if (!getDatetime().equals("undefined") && !getDatetime().equals("")) {
				Timestamp ts = format(this.datetime);
				getParam().put("beginTime", ts);
			}
			if (!name.equals("")) {
				param.put("userName", name);
				// System.out.println(name);
			}
			if (bumen != 0) {
				param.put("departId", bumen);
			}
			if (jiguo != 0) {
				param.put("branchId", jiguo);
			}

			precontractService.assemblePage(super.getBasePage(), param);

			getDatas().put("total", super.getBasePage().getTotalRecords());
			getDatas().put("rows", super.getBasePage().getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 加载当前用户 选中日期的所有日程
	 * 
	 * @return
	 */
	public String loadDateSchedule() {
		// System.out.println("datetime-----" + datetime);
		Userinfo userinfo = (Userinfo) getSession().get("user");
		// System.out.println(userinfo.getUserId());
		List<Schedule> scheduleList = null;
		try {
			Timestamp ts = format(this.datetime);
			scheduleList = getPrecontractService().getAllObject(
					userinfo.getUserId(), ts, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> datas = getDatas();
		datas.clear();
		if (scheduleList != null && !scheduleList.isEmpty()) {
			datas.put("total", scheduleList.size());
			datas.put("rows", scheduleList);
		}
		return SUCCESS;
	}

	private Timestamp format(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = date + " 00:00:00";
		// System.out.println(datetime);
		// System.out.println(time);
		Timestamp ts = Timestamp.valueOf(time);
		return ts;
	}

	public IPrecontractService getPrecontractService() {
		return precontractService;
	}

	public void setPrecontractService(IPrecontractService precontractService) {
		this.precontractService = precontractService;
	}

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public int getRows() {
		return rows;
	}

	@Override
	public void setRows(int rows) {
		this.rows = rows;
	}

	@Override
	public String show() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBumen() {
		return bumen;
	}

	public void setBumen(int bumen) {
		this.bumen = bumen;
	}

	public int getJiguo() {
		return jiguo;
	}

	public void setJiguo(int jiguo) {
		this.jiguo = jiguo;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getDatetime() {
		return datetime;
	}
}