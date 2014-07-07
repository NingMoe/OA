package com.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oa.dao.impl.DepartDaoImpl;
import com.oa.entity.Branchinfo;
import com.oa.entity.Departinfo;
import com.oa.entity.Roleinfo;
import com.oa.entity.Userinfo;
import com.oa.service.IDepartService;
import com.oa.util.Message;
import com.oa.util.Msgtype;
import com.oa.util.RoleConstant;
import com.oa.util.Tree;

public class DepartServiceImpl extends BaseServiceImpl<Departinfo> implements
		IDepartService {

	DepartDaoImpl departDaoImpl = new DepartDaoImpl();

	private Tree uniqueNode;

	@Override
	public List<Branchinfo> getAllBranch() {
		// TODO Auto-generated method stub
		return getDao().findByHql("from Branchinfo");
	}

	/**
	 * (填充depart) 更新部门信息时,根据用户提交的机构Id和用户Id ,获取机构对象和负责人对象 ,装入对象到部门,实现部门更新.
	 * 
	 * @return 装入完毕的对象
	 */
	@Override
	public Departinfo onSaveOrUpdate(Departinfo depart) {

		depart.setBranchinfo((Branchinfo) getDao().findById(Branchinfo.class,
				depart.getBranchinfo().getBranchId()));

		depart.setUserinfo((Userinfo) getDao().findById(Userinfo.class,
				depart.getUserinfo().getUserId()));

		return depart;
	}

	@Override
	public boolean prop_val_exists(String prop, Object val) {

		List l = getDao()
				.findByProperty(Departinfo.class, prop, val.toString());

		if (l != null && !l.isEmpty()) {
			return true;
		}
		return false;

	}

	@Override
	public void saveOrUpdate(Departinfo t) {
		super.saveOrUpdate(t);
		
		//更行用户的departId
		String hql = "update Userinfo u set u.departId=" + t.getDepartId()
				+ " where u.userId='" + t.getUserinfo().getUserId()+"'";
		getDao().executeHql(hql);
	}

	@Override
	public List getBranchStaff() {

		this.uniqueNode = null;

		List r = new ArrayList();
		Map attrs = null;

		List<Branchinfo> bList = getDao().findByHql("from Branchinfo");

		for (Branchinfo b : bList) {
			buildUniqueNode();
			getCurrUniqueNode().setText(b.getBranchName());
			attrs = new HashMap();
			attrs.put("branchId", b.getBranchId());
			getCurrUniqueNode().setAttributes(attrs);
			getCurrUniqueNode().setIconCls("hr-derpart-manage");
			Tree t1 = getCurrUniqueNode();
			if (b.getDepartinfos() == null || b.getDepartinfos().size() < 1)
				continue;
			else
				r.add(getCurrUniqueNode());
			for (Object obj : b.getDepartinfos().toArray()) {
				Departinfo d = (Departinfo) obj;
				buildUniqueNode();
				getCurrUniqueNode().setText(d.getDepartName());
				getCurrUniqueNode().setPid(t1.getId());
				attrs = new HashMap();
				attrs.put("departId", d.getDepartId());
				getCurrUniqueNode().setAttributes(attrs);
				getCurrUniqueNode().setIconCls("hr-branch-manage");
				Tree t2 = getCurrUniqueNode();
				if (d.getEmployees() == null || d.getEmployees().size() < 1)
					continue;
				else
					r.add(getCurrUniqueNode());
				for (Userinfo emp : d.getEmployees()) {
					Map<String, Object> empinfo = new HashMap<String, Object>();
					empinfo.put("id", emp.getUserId());
					empinfo.put("text", emp.getUserName());
					empinfo.put("pid", t2.getId());
					Map m = new HashMap();
					m.put("userId", emp.getUserId());
					empinfo.put("attributes", m);
					if (emp == d.getUserinfo()) {
						// 当前为负责人
						empinfo.put("iconCls", "role-manage");
					} else {
						empinfo.put("iconCls", "hr-user-manage");
					}
					r.add(empinfo);
				}
			}

		}

		return r;
	}

	public Tree buildUniqueNode() {
		if (uniqueNode == null) {
			uniqueNode = new Tree(1);
		} else {
			Integer uniqueId = uniqueNode.getId();
			uniqueNode = new Tree(++uniqueId);
		}
		return uniqueNode;
	}

	public Tree getCurrUniqueNode() {
		return uniqueNode;
	}

	@Override
	public Set<Userinfo> getDeparts(Departinfo departinfo, Userinfo userinfo) {
		Departinfo departinfo2 = (Departinfo) getDao().findById(
				departinfo.getClass(), departinfo.getDepartId());
		Set<Userinfo> userinfos = new HashSet<Userinfo>(
				departinfo2.getEmployees());
		
		// for (Userinfo userinfo : userinfos) {
		// System.out.println(userinfo.getUserName());
		// }
		return userinfos;
	}
	
	public String delete(Integer departId,Userinfo currUser,Message msg) {
		if(currUser!=null){
			if(currUser.getDepartId()==departId){
				msg.setMsgtype(Msgtype.warning);
				msg.setMsg("当前用户无法删除其所属部门!");
				return "success";
			}
			for(Roleinfo role : currUser.getRoleinfos()){
				if(role.getRoleId().intValue()==RoleConstant.MAX_LEVEL.intValue()){
					Departinfo d = getById(departId);
					delete(d);
					msg.setMsgtype(Msgtype.info);
					msg.setMsg("删除部门--"+d.getDepartName()+"--成功!");
					return "success";
				}
			}
			msg.setMsgtype(Msgtype.warning);
			msg.setMsg("该操作需要最高权限!");
			return "success";
		}else{
			return "error";
		}
	}
	
}
