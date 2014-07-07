package com.oa.action.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import com.oa.action.IScheduleAction;
import com.oa.entity.Branchinfo;
import com.oa.entity.Departinfo;
import com.oa.entity.Schedule;
import com.oa.entity.Userinfo;
import com.oa.service.IBranchService;
import com.oa.service.IDepartService;
import com.oa.service.IPrecontractService;
import com.oa.service.IScheduleService;
import com.oa.service.IUserService;

public class ScheduleAction extends Action4easyui<Schedule> implements
		IScheduleAction {

	private IScheduleService scheduleService;
	private IBranchService branchService;
	private IDepartService departService;
	private IPrecontractService precontractService;
	private IUserService userService;
	private List<Branchinfo> branchinfos;
	private List<Departinfo> departinfos;
	private List dates;
	private int foreiId;

	/*
	 * 一些日程的对象和字段
	 */
	private Schedule schedule;
	private Timestamp startTime;
	private Timestamp endTime;

	// 删除一个对象要的ID;
	private int scheduleId;

	// 查询条件
	private Timestamp datetime;
	private String name;
	private int bumen;
	private int jiguo;

	private int bumen_dialog;
	private int jiguo_dialog;

	// 条件查询的map
	private Map<String, Object> param;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int branchId;
	private int departId;
	private Data beingTime;
	private String createUser;

	// Userinfo userinfo
	private Userinfo userinfo;

	@Override
	public String add() {
		return null;
	}

	@Override
	public String remove() {
		return null;
	}

	@Override
	public String update() {
		return null;
	}

	@Override
	public String show() {
		return null;
	}

	/**
	 * 删除Schedule
	 * 
	 * @return
	 */
	public String deleteObj() {

		try {
			// 要删除的对象
			Schedule schedule_del = this.getBaseService().getById(scheduleId);

			// 父类删除的方法
			this.getBaseService().delete(schedule_del);
			Map<String, Object> datas = getDatas();
			datas.clear();
			datas.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 更新添加
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		Date date = new Date();
		String dateStr = "";
		// format的格式可以日期
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			dateStr = sdf.format(date);
			schedule.setCreateTime(Timestamp.valueOf(dateStr));
			schedule.setIfPrivate("yes");
		} catch (Exception e) {
			getDatas().put("success", false);
		}

		// 保存 或者 修改 日程
		try {
			Userinfo userinfo = (Userinfo) getSession().get("user");
			schedule.setCreateUser(userinfo.getUserId());
			super.getBaseService().saveOrUpdate(schedule);
		} catch (Exception e) {
			getDatas().put("success", false);
		}

		// 用户 选择了对应的部门或者对象的机构， 机构/部门 下所有员工添加上日程
		try {
			// 即保存 precontract 表
			Map<String, Object> params = new HashMap<String, Object>();
			if (bumen_dialog != 0) {
				params.put("departId", bumen_dialog);
			} else if (jiguo_dialog != 0) {
				params.put("branchId", jiguo_dialog);
			}

			if (params != null && !params.isEmpty()) {
				List<Userinfo> userinfos = userService.getObjByMap(params);
				precontractService.deleteWhere(schedule);
				if (userinfos != null && !userinfos.isEmpty()) {
					precontractService.saveOrUpdate(schedule, userinfos);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		getDatas().put("success", true);
		return SUCCESS;
	}

	/**
	 * 前台请求机构数据
	 */
	public String loadBranch() {
		System.out.println("........loadBranch");
		branchinfos = branchService.getAllObject();
		Map<String, Object> datas = getDatas();
		datas.clear();
		datas.put("branchInfos", branchinfos);
		return SUCCESS;
	}

	/**
	 * 加载部门
	 * 
	 * @return
	 */
	public String loadDepart() {
		// System.out.println("........loadDepart");
		departinfos = departService.getAllObject(foreiId, "branchId");
		Map<String, Object> datas = getDatas();
		datas.clear();
		datas.put("departinfos", departinfos);
		return SUCCESS;
	}

	/**
	 * 加载日期所有日期
	 * 
	 * @return
	 */
	public String loadDates() {
		userinfo = (Userinfo) getSession().get("user");
		// System.out.println(userinfo.getPrecontracts().size());
		// System.out.println(userinfo.getUserId());r
		dates = scheduleService.loadDates(userinfo.getUserId());
		Map<String, Object> datas = getDatas();
		datas.clear();
		datas.put("dates", dates);
		return SUCCESS;
	}

	/*
	 * get && set
	 */
	public List<Branchinfo> getBranchinfos() {
		return branchinfos;
	}

	public void setBranchinfos(List<Branchinfo> branchinfos) {
		this.branchinfos = branchinfos;
	}

	public IBranchService getBranchService() {
		return branchService;
	}

	public void setBranchService(IBranchService branchService) {
		this.branchService = branchService;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}

	public Data getBeingTime() {
		return beingTime;
	}

	public void setBeingTime(Data beingTime) {
		this.beingTime = beingTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public IDepartService getDepartService() {
		return departService;
	}

	public void setDepartService(IDepartService departService) {
		this.departService = departService;
	}

	public List<Departinfo> getDepartinfos() {
		return departinfos;
	}

	public void setDepartinfos(List<Departinfo> departinfos) {
		this.departinfos = departinfos;
	}

	public int getForeiId() {
		return foreiId;
	}

	public void setForeiId(int foreiId) {
		this.foreiId = foreiId;
	}

	public List getDates() {
		return dates;
	}

	public void setDates(List dates) {
		this.dates = dates;
	}

	public IScheduleService getScheduleService() {
		return scheduleService;
	}

	public void setScheduleService(IScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
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

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public int getBumen_dialog() {
		return bumen_dialog;
	}

	public void setBumen_dialog(int bumen_dialog) {
		this.bumen_dialog = bumen_dialog;
	}

	public int getJiguo_dialog() {
		return jiguo_dialog;
	}

	public void setJiguo_dialog(int jiguo_dialog) {
		this.jiguo_dialog = jiguo_dialog;
	}

	public IPrecontractService getPrecontractService() {
		return precontractService;
	}

	public void setPrecontractService(IPrecontractService precontractService) {
		this.precontractService = precontractService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}