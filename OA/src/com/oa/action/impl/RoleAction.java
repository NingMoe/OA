package com.oa.action.impl;

import com.oa.action.IRoleAction;
import com.oa.entity.Roleinfo;

public class RoleAction extends Action4easyui<Roleinfo> implements IRoleAction{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer roleId;
    private String roleName;
    private String roleDesc;
	
	@Override
	public String add() {
		// TODO Auto-generated method stub
		getBaseService().save(new Roleinfo(roleName,roleDesc));
		return SUCCESS;
	}

	@Override
	public String remove() {
		// TODO Auto-generated method stub

		getBaseService().delete(new Roleinfo(Integer.parseInt(getId()),"",""));
		return SUCCESS;
	}

	@Override
	public String update() {
		// TODO Auto-generated method stub
		getBaseService().update(new Roleinfo(roleId,roleName,roleDesc));
		return SUCCESS;
	}

	@Override
	public String show() {
		// TODO Auto-generated method stub

		return null;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}
