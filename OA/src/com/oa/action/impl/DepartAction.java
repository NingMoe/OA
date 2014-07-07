package com.oa.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oa.action.IDepartAction;
import com.oa.entity.Branchinfo;
import com.oa.entity.Departinfo;
import com.oa.entity.Roleinfo;
import com.oa.entity.Userinfo;
import com.oa.service.IDepartService;
import com.oa.service.impl.DepartServiceImpl;
import com.oa.util.Message;
import com.oa.util.RoleConstant;
import com.oa.util.Tree;

public class DepartAction extends Action4easyui<Departinfo> implements
		IDepartAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Branchinfo> branchinfos;
	@Deprecated
	private List<Map<String, Object>> employeeTree;
	private List<Tree> staffTree;

	private Departinfo depart;
	private boolean exists;
	private Message msg;

	// ////////////////////////////// method
	// ////////////////////////////////////////

	/**
	 * �����������š�Ա������Ϊ��,������
	 * 
	 * @return
	 */
	@Deprecated
	public List<Map<String, Object>> buildTree() {

		List<Map<String, Object>> items, secondItems, thirdItems;

		// �򵥸���ʼֵ
		items = secondItems = thirdItems = null;

		Map<String, Object> item, secondItem, thirdItem;

		// ����һ�������˶�������,Ϊǰ̨�ṩ����
		final Map<String, String> attr = new HashMap<String, String>();
		attr.put("nodetype", "userinfo");

		List<Branchinfo> branchinfos = getReallyService().getAllBranch();

		if (branchinfos != null && !branchinfos.isEmpty()) {
			// ��ʼ��������
			items = new ArrayList<Map<String, Object>>();

			for (Branchinfo b : branchinfos) {

				item = new HashMap<String, Object>();

				item.put("id", b.getBranchId());

				item.put("text", b.getBranchName());

				if (b.getDepartinfos() != null && !b.getDepartinfos().isEmpty()) {

					// ��ʼ���ڶ���(����)����
					secondItems = new ArrayList<Map<String, Object>>();

					Object[] objs = b.getDepartinfos().toArray();

					for (Object obj : objs) {

						Departinfo d = (Departinfo) obj;

						secondItem = new HashMap<String, Object>();

						secondItem.put("id", d.getDepartId());

						secondItem.put("text", d.getDepartName());

						if (d.getUserinfo() != null) {

							// ��ʼ��������(������)����
							thirdItems = new ArrayList<Map<String, Object>>();

							thirdItem = new HashMap<String, Object>();

							thirdItem.put("id", d.getUserinfo().getUserId());

							thirdItem
									.put("text", d.getUserinfo().getUserName());

							thirdItem.put("attribute", attr);

							thirdItems.add(thirdItem);

							secondItem.put("children", thirdItems);
						}
						secondItems.add(secondItem);
					}
					item.put("children", secondItems);
				}
				items.add(item);
			}
		}
		return null;
	}

	@Override
	public String remove() {
		
		return getDepartService().delete(Integer.parseInt(getId()),
				(Userinfo) getSession().get("user"), getMsg());

	}

	public IDepartService getDepartService() {
		return (IDepartService) getBaseService();
	}

	@Override
	public String checkDepartName() {
		setExists(getReallyService().prop_val_exists("departName",
				getDepart().getDepartName()));
		return SUCCESS;
	}

	@Override
	public String show() {
		setDepart(getBaseService().getById(depart.getDepartId()));
		return SUCCESS;
	}

	public String addBefore() {
		setBranchinfos(getReallyService().getAllBranch());
		return SUCCESS;
	}

	public String showEmployeeTree() {
		// setEmployeeTree(buildTree());
		setStaffTree(getReallyService().getBranchStaff());
		return SUCCESS;
	}

	@Override
	public DepartServiceImpl getReallyService() {
		return (DepartServiceImpl) getBaseService();
	}

	@Override
	public String addOrUpdate() {

		// ���depart
		getReallyService().onSaveOrUpdate(getDepart());

		getReallyService().saveOrUpdate(getDepart());

		return NONE;
	}

	// /////////////////////////// getter&setter
	// //////////////////////////////////

	public void setBranchinfos(List<Branchinfo> branchinfos) {
		this.branchinfos = branchinfos;
	}

	public List<Branchinfo> getBranchinfos() {
		return branchinfos;
	}

	public void setEmployeeTree(List<Map<String, Object>> employeeTree) {
		this.employeeTree = employeeTree;
	}

	public List<Map<String, Object>> getEmployeeTree() {
		return employeeTree;
	}

	public void setDepart(Departinfo depart) {
		this.depart = depart;
	}

	public Departinfo getDepart() {
		return depart;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public boolean isExists() {
		return exists;
	}

	public void setStaffTree(List<Tree> staffTree) {
		this.staffTree = staffTree;
	}

	public List<Tree> getStaffTree() {
		return staffTree;
	}

	public Message getMsg() {
		return  msg==null?msg=new Message():msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

}
