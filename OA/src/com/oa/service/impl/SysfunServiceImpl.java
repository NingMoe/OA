package com.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oa.dao.IRoleDao;
import com.oa.dao.IRolerightDao;
import com.oa.dao.ISysfunDao;
import com.oa.entity.Roleinfo;
import com.oa.entity.Roleright;
import com.oa.entity.Sysfun;
import com.oa.entity.Userinfo;
import com.oa.service.ISysfunService;
import com.oa.util.Tree;

public class SysfunServiceImpl extends BaseServiceImpl<Sysfun> implements
		ISysfunService {

	private ISysfunDao sysfunDao;

	private IRoleDao roleDao;

	private IRolerightDao rolerightDao;

	@Override
	public List<Sysfun> getAll(Userinfo userinfo) {
		List<Sysfun> sysfuns = null;
		try {
			System.out.println("..........getAllTreeNode............1");
			List temps = sysfunDao.getAll(userinfo);
			Iterator it = temps.iterator();
			sysfuns = new ArrayList<Sysfun>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Sysfun sysfun = (Sysfun) obj[1];
				// System.out.println(obj[0].getClass());
				// System.out.println(obj[1].getClass());
				// System.out.println(obj[2].getClass());
				// System.out.println(obj[3].getClass());
				// System.out.println(obj[4].getClass());
				sysfuns.add(sysfun);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sysfuns;
	}

	/**
	 * (non-Javadoc) 得到用户的权限
	 * 
	 * @see com.oa.service.ISysfunService#getAllTreeNode(com.oa.entity.Userinfo)
	 */
	@Override
	public List<Tree> getAllTreeNode(Userinfo userinfo) {
		List<Tree> trees = null;
		// StringBuffer hql = new StringBuffer(
		// "SELECT distinct sysfun.nodeId,sysfun FROM Userinfo us");
		// hql.append(" join us.roleinfos as roleinfos");
		// hql.append(" join roleinfos.rolerights as rolerights");
		// hql.append(" join rolerights.sysfun as sysfun");
		//
		//
		// Map<String, Object> params = new HashMap<String, Object>();
		// if (userinfo != null) {
		// hql.append(" where us.userId=:userid");
		// params.put("userid", userinfo.getUserId());
		// } else {
		// params = null;
		// }
		// hql.append(" order by sysfun.nodeId");
		List<Sysfun> sysfuns = null;
		try {
			System.out.println("..........getAllTreeNode............1");
			List temps = sysfunDao.getAll(userinfo);
			Iterator it = temps.iterator();
			sysfuns = new ArrayList<Sysfun>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Sysfun sysfun = (Sysfun) obj[1];
				// System.out.println(obj[0].getClass());
				// System.out.println(obj[1].getClass());
				// System.out.println(obj[2].getClass());
				// System.out.println(obj[3].getClass());
				// System.out.println(obj[4].getClass());
				sysfuns.add(sysfun);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		trees = copyProperty(trees, sysfuns);
		return trees;
	}

	private List<Tree> copyProperty(List<Tree> trees, List<Sysfun> sysfuns) {
		if (sysfuns != null && sysfuns.size() > 0) {
			trees = new ArrayList<Tree>();
			for (Sysfun sysfun : sysfuns) {
				Tree tree = new Tree();

				tree.setIconCls(sysfun.getIconCls());

				Map<String, Object> attributes = new HashMap<String, Object>();

				attributes.put("url", sysfun.getAttributes());

				tree.setAttributes(attributes);
				Sysfun pSysfun = sysfun.getSysfun();
				Set set = sysfun.getSysfuns();

				tree.setText(sysfun.getDisplayName());
				tree.setId(sysfun.getNodeId());
				if (pSysfun != null && set != null) {
					tree.setPid(pSysfun.getNodeId());
					tree.setState("opened");

					for (Object object : set) {
						Sysfun tSysfun = (Sysfun) object;
						trees.add(tree);
					}

				}
				trees.add(tree);
			}
		}
		return trees;
	}

	/**
	 * 得到角色有的权限
	 */
	@Override
	public List<Tree> getAllTreeNode(int roleId) {
		List<Tree> trees = null;
		StringBuffer hql = new StringBuffer(
				"SELECT distinct sysfun.nodeId,sysfun FROM Userinfo us");
		hql.append(" join us.roleinfos as roleinfos");
		hql.append(" join roleinfos.rolerights as rolerights");
		hql.append(" join rolerights.sysfun as sysfun");
		hql.append(" where roleinfos.roleId=:roleId");
		Map<String, Object> params = null;
		if (roleId != 0) {
			params = new HashMap<String, Object>();
			params.put("roleId", roleId);
		}
		List<Sysfun> sysfuns = null;
		try {
			System.out.println("..........getAllTreeNode............1");
			List temps = sysfunDao.findByHql(hql.toString(), params, null);
			Iterator it = temps.iterator();
			sysfuns = new ArrayList<Sysfun>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Sysfun sysfun = (Sysfun) obj[1];
				System.out.println(obj[0].getClass());
				// System.out.println(obj[1].getClass());
				// System.out.println(obj[2].getClass());
				// System.out.println(obj[3].getClass());
				// System.out.println(obj[4].getClass());
				sysfuns.add(sysfun);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (sysfuns != null && sysfuns.size() > 0) {
			trees = new ArrayList<Tree>();
			for (Sysfun sysfun : sysfuns) {
				Tree tree = new Tree();

				tree.setIconCls(sysfun.getIconCls());

				Map<String, Object> attributes = new HashMap<String, Object>();

				attributes.put("url", sysfun.getAttributes());

				tree.setAttributes(attributes);
				Sysfun pSysfun = sysfun.getSysfun();
				Set set = sysfun.getSysfuns();

				tree.setText(sysfun.getDisplayName());
				tree.setId(sysfun.getNodeId());
				if (pSysfun != null && set != null) {
					tree.setPid(pSysfun.getNodeId());
					tree.setState("opened");
					tree.setChecked(true);
				}
				trees.add(tree);
			}
		}
		return trees;
	}

	/**
	 * 得到权限树，包括有的权限被选中
	 */
	@Override
	public List<Tree> getAllTreeNodeChecked(List<Tree> treeChecked) {
		List<Tree> trees = null;
		StringBuffer hql = new StringBuffer("FROM Sysfun");
		List<Tree> temps = null;
		try {

			// 通过HQL 得到所有SYSFUN
			List<Sysfun> sysfuns = sysfunDao.findByHql(hql.toString(), null,
					null);

			if (sysfuns != null && sysfuns.size() > 0) {
				temps = copyPropertyOnChecked(sysfuns);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 把tree 放在map里 以答到可是检测的目的
		Map<Integer, Tree> map = new HashMap<Integer, Tree>();
		trees = operTree(treeChecked, temps, map);

		return trees;
	}

	// copy选中的属性
	private List<Tree> copyPropertyOnChecked(List<Sysfun> sysfuns) {
		List<Tree> temps;
		temps = new ArrayList<Tree>();

		// copy sysfun 属性到 tree中 才添加
		for (Sysfun sysfun : sysfuns) {
			Tree tree = new Tree();

			Map<String, Object> attributes = new HashMap<String, Object>();

			attributes.put("url", sysfun.getAttributes());

			Sysfun pSysfun = sysfun.getSysfun();
			Set set = sysfun.getSysfuns();

			tree.setText(sysfun.getDisplayName());
			tree.setId(sysfun.getNodeId());
			if (pSysfun != null && set != null) {
				tree.setPid(pSysfun.getNodeId());
				tree.setState("opened");
				tree.setChecked(false);
			}
			tree.setAttributes(attributes);
			tree.setIconCls(sysfun.getIconCls());

			temps.add(tree);
		}
		return temps;
	}

	private List<Tree> operTree(List<Tree> treeChecked, List<Tree> temps,
			Map<Integer, Tree> map) {
		List<Tree> trees;
		try {
			if (temps != null) {
				for (Tree tree : temps) {
					map.put(tree.getId(), tree);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 替换MAP中的tree
		if (treeChecked != null) {
			for (Tree tree : treeChecked) {
				int id = tree.getId();
				if (map.containsKey(id)) {
					map.remove(id);
					map.put(id, tree);
				}
			}
		}
		trees = new ArrayList<Tree>();

		// 把map里tree 遍历到trees里
		for (Integer key : map.keySet()) {
			trees.add(map.get(key));
		}
		return trees;
	}

	/**
	 * 给角色授权
	 */
	@Override
	public String saveAuthorizeRole(String[] roleIds, int roleId) {
		String isSuccess = "none";

		// 转成 roleId int
		int len = roleIds.length;
		Integer[] roleSysfuns = convertRoleIds(roleIds, len);
		/*
		 * 开始做进行更新操作
		 */
		// 1. 查询到roleI对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		Roleinfo roleinfo = null;
		try {
			List<Roleinfo> list = roleDao.findByHql(
					"From Roleinfo where roleId=:roleId", map, null);
			if (list != null && !list.isEmpty()) {
				roleinfo = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2. 查询到所有 roleSysfuns中所有功能的对象 返回roles 的一个Set
		// 2.1 删除所有权限
		Set rolerights = null;
		if (roleinfo != null) {
			rolerights = roleinfo.getRolerights();
		}
		try {
			Roleright o = null;
			if (rolerights != null && !rolerights.isEmpty()) {
				for (Object object : rolerights) {
					o = (Roleright) object;
					if (o != null) {
						rolerightDao.delete(o);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 2.2 添加传递过来的全部权限
		// 2.21 通过 roleSysfuns 数组 产生所有 权限对象
		List<Sysfun> sysfuns = sysfunDao.findList(roleSysfuns);
		// 2.22 把得到 sysfuns 与 roleinfo new rolerights 的set
		rolerights = rolerightDao.newRolerights(sysfuns, roleinfo);

		// 测试用的伪代码
		// roleright1.setRoleinfo(roleinfo);
		// Sysfun sysfun1 = (Sysfun) sysfunDao.findById(Sysfun.class, 101001);
		// roleright1.setSysfun(sysfun1);
		//
		// Roleright roleright2 = new Roleright();
		// roleright2.setRoleinfo(roleinfo);
		// Sysfun sysfun2 = (Sysfun) sysfunDao.findById(Sysfun.class, 101002);
		// roleright2.setSysfun(sysfun2);
		//
		// rolerights.add(roleright1);
		// rolerights.add(roleright2);

		// 2.22 rolerights set进role
		// if (roleinfo != null) {
		// roleinfo.setRolerights(rolerights);
		// }
		// 2.3 更新
		rolerightDao.saveSet(rolerights);

		isSuccess = "success";

		// 3. 把 所有

		return isSuccess;
	}

	/**
	 * 转换roleId String 类型数组转成
	 * 
	 * @param roleIds
	 * @param len
	 */
	private Integer[] convertRoleIds(String[] roleIds, int len) {
		Integer[] roles = null;
		if (roleIds != null && len > 0) {
			roles = new Integer[len];
			for (int i = 0; i < roles.length; i++) {

				roles[i] = Integer.parseInt(roleIds[i]);

			}
		}
		return roles;
	}

	public ISysfunDao getSysfunDao() {
		return sysfunDao;
	}

	public void setSysfunDao(ISysfunDao sysfunDao) {
		this.sysfunDao = sysfunDao;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IRolerightDao getRolerightDao() {
		return rolerightDao;
	}

	public void setRolerightDao(IRolerightDao rolerightDao) {
		this.rolerightDao = rolerightDao;
	}

}
