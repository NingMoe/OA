package com.oa.action.impl;

import java.util.List;
import java.util.regex.Pattern;

import com.oa.action.ISysfunAction;
import com.oa.entity.Roleinfo;
import com.oa.entity.Userinfo;
import com.oa.service.ISysfunService;
import com.oa.util.Tree;

public class SysfunAction extends Action4easyui<Roleinfo> implements
		ISysfunAction {

	private ISysfunService sysfunService;
	private int roleId;
	private String params;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Tree> trees;
	private String msg;

	@Override
	public String show() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 得到所有节点
	 */
	@Override
	public String getAllTreeNode() {
		Userinfo userinfo = (Userinfo) getSession().get("user");
		trees = sysfunService.getAllTreeNode(userinfo);
		return SUCCESS;
	}

	/**
	 * 得到角色节点树，如果 角色有权限其节点checked 属性为true
	 * 
	 * @return
	 */
	public String getAllTreeNodeByRole() {
		List<Tree> list = sysfunService.getAllTreeNode(roleId);
		trees = sysfunService.getAllTreeNodeChecked(list);
		return SUCCESS;
	}

	/**
	 * 授权方法
	 * 
	 * @return
	 */
	public String authorizeRole() {
		System.out
				.println(".......sysfunAction authorizeRole (action中授权)......");
		String[] roleIds = Pattern.compile(",").split(params);
		try {
			if (roleIds!=null&& !roleIds[0].equals("")) {
				msg = sysfunService.saveAuthorizeRole(roleIds, roleId);
			}else{
				msg="none";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<Tree> getTrees() {
		return trees;
	}

	public void setTrees(List<Tree> trees) {
		this.trees = trees;
	}

	public ISysfunService getSysfunService() {
		return sysfunService;
	}

	public void setSysfunService(ISysfunService sysfunService) {
		this.sysfunService = sysfunService;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
