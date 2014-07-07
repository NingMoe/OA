package com.oa.action.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.oa.action.IBaseAction;
import com.oa.action.ISignAction;
import com.oa.entity.Departinfo;
import com.oa.entity.Manualsign;
import com.oa.entity.Userinfo;
import com.oa.service.IDepartService;
import com.oa.service.ISignService;
import com.oa.service.IUserService;

public class SignAction extends BaseAction2<Manualsign> implements IBaseAction,
		ISignAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IDepartService departService;
	private ISignService signService;
	private IUserService userService;
	private Map<String, Object> datas = new HashMap<String, Object>();

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	private Userinfo userinfo;
	private Manualsign manualsign;
	private Departinfo depart;

	private String signTime;
	private String signDesc;

	// /
	@Override
	public String add_input() {
		try {
			input_output(0);
		} catch (Exception e) {
			getDatas().put("r", "false");
			e.printStackTrace();
		}

		return SUCCESS;
	}

	@Override
	public String add_output() {
		try {
			input_output(1);
		} catch (Exception e) {
			getDatas().put("r", false);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private void input_output(int tag) {
		userinfo = (Userinfo) getSession().get("user");

		depart = departService.getById(userinfo.getDepartId());

		manualsign.setUserinfo(userinfo);
		manualsign.setSignTag(tag);
		Date date = new Date();
		manualsign.setSignTime(new Timestamp(date.getTime()));
		signService.save(manualsign);
		// 查出 Session中ID所对象的那个用户的。。。
		getDatas().put("r", true);
	}

	@Override
	public String remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String show() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showPageByMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public ISignService getSignService() {
		return signService;
	}

	public void setSignService(ISignService signService) {
		this.signService = signService;
	}

	public Manualsign getManualsign() {
		return manualsign;
	}

	public void setManualsign(Manualsign manualsign) {
		this.manualsign = manualsign;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getSignDesc() {
		return signDesc;
	}

	public void setSignDesc(String signDesc) {
		this.signDesc = signDesc;
	}

	public void setDepart(Departinfo depart) {
		this.depart = depart;
	}

	public Departinfo getDepart() {
		return depart;
	}

	public void setDepartService(IDepartService departService) {
		this.departService = departService;
	}

	public IDepartService getDepartService() {
		return departService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public Map<String, Object> getDatas() {
		return datas;
	}

	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}

}