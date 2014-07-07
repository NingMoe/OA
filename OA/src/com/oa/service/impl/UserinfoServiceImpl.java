package com.oa.service.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.oa.entity.Roleinfo;
import com.oa.entity.Userinfo;
import com.oa.entity.Userstate;
import com.oa.service.IUserService;

public class UserinfoServiceImpl extends BaseServiceImpl<Userinfo> implements
		IUserService {

	/* private IFileinfoService fileService; */

	@Override
	public boolean login(Userinfo userinfo) {
		List<Userinfo> list = getDao().findByProperty(Userinfo.class, "userId",
				userinfo.getUserId());

		if (list != null && list.size() > 0) {

			Userinfo u = list.get(0);
			if (u.getPassWord().equals(userinfo.getPassWord())) {
				BeanUtils.copyProperties(u, userinfo);
				userinfo.getRoleinfos().size();
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean updatePwd(Userinfo user, String newPwd) {
		Userinfo temp = (Userinfo) getDao().findById(user.getClass(),
				user.getUserId());
		if (temp.getPassWord().equals(user.getPassWord())) {
			temp.setPassWord(newPwd);
			user = temp;
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean userId_isExists(String userId) {
		int result = 0;
		try {
			result = ((BigInteger) getDao().findBySql(
					"select count(*) from userinfo as u where u.userId='"
							+ userId + "'").get(0)).intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 锟斤拷锟轿拷锟斤拷锟斤拷锟斤拷
	 * 
	 * @param user
	 *            锟矫伙拷锟结交锟�锟斤拷锟绞弊帮拷锟侥简单讹拷锟斤拷,
	 */
	@Override
	public Userinfo simple2FullObj(Userinfo user) {

		// 锟矫伙拷状态
		user.setUserstate((Userstate) getDao().findById(Userstate.class,
				user.getUserstate().getUserStateId()));

		// 锟矫伙拷锟斤拷色
		Object[] objs = user.getRoleinfos().toArray();
		user.getRoleinfos().clear();// 锟斤拷锟�
		for (int i = 0; i < objs.length; i++) {
			int roleId = ((Roleinfo) objs[i]).getRoleId();
			user.getRoleinfos().add(// 锟斤拷咏锟缴拷锟斤拷锟�
					(Roleinfo) getDao().findById(Roleinfo.class, roleId));
		}
		return user;

	}

	@Override
	public List<?> getOtherObjList(Class clazz) {
		return getDao().findByHql("from " + clazz.getName());
	}

	@Override
	public Object getOtherObjById(Class clazz, Serializable id) {
		return getDao().findById(clazz, id);
	}

	/**
	 * 通过 部门或者机构查询到相应用户
	 */
	@Override
	public List<Userinfo> getObjByMap(Map<String, Object> params) {
		List<Userinfo> list = null;
		StringBuffer hql = new StringBuffer();
		/*
		 * 通过不能参加生成不HQL语句
		 */
		hql.append("SELECT distinct user.userId,user FROM Branchinfo bra");
		hql.append(" right join bra.departinfos dep");
		hql.append(" right join dep.employees user");
		hql.append(" where 1=1");
		if (params.containsKey("departId")) {
			hql.append(" and dep.departId=:departId");
		}
		if (params.containsKey("branchId")) {
			hql.append(" and bra.branchId=:branchId");
		}
		List li = dao.findByHql(hql.toString(), params, null);
		Iterator iterator = li.iterator();
		if (iterator != null) {
			list=new ArrayList<Userinfo>();
			// 把查出的数据放到schedules
			while (iterator.hasNext()) {
				Object[] o = (Object[]) iterator.next();
				Userinfo userinfo = (Userinfo) o[1];
				list.add(userinfo);
			}
		}
		
		return list;
	}

}
