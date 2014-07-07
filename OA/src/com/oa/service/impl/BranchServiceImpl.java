package com.oa.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.oa.entity.Branchinfo;
import com.oa.entity.Departinfo;
import com.oa.entity.Roleinfo;
import com.oa.entity.Userinfo;
import com.oa.service.IBranchService;
import com.oa.util.Message;
import com.oa.util.Msgtype;
import com.oa.util.RoleConstant;

public class BranchServiceImpl extends BaseServiceImpl<Branchinfo> implements
		IBranchService {

	@Override
	public Set<Userinfo> getBranchsFieldUser(Branchinfo branchinfo) {
		Set<Userinfo> userinfos = new HashSet<Userinfo>();

		Branchinfo branchinfo1 = (Branchinfo) getDao().findById(
				branchinfo.getClass(), branchinfo.getBranchId());

		Set<Departinfo> departinfos = branchinfo1.getDepartinfos();
		for (Departinfo departinfo : departinfos) {
			List<Userinfo> employees = departinfo.getEmployees();
			for (Userinfo userinfo : employees) {
				userinfos.add(userinfo);
			}
		}

		// System.out.println("-----------------getBranchsFieldUser------------------");
		// for (Userinfo userinfo : userinfos) {
		// System.out.println(userinfo.getUserName());
		// }
		return userinfos;
	}
	
	@Override
	public String delete(Integer bid, Userinfo currUser, Message msg) {
		if(currUser!=null){
			if(currUser.getDepartinfo().getBranchinfo().getBranchId()==bid){
				msg.setMsgtype(Msgtype.warning);
				msg.setMsg("当前用户无法删除其所属机构!");
				return "success";
			}
			for(Roleinfo role : currUser.getRoleinfos()){
				if(role.getRoleId().intValue()==RoleConstant.MAX_LEVEL.intValue()){
					Branchinfo b = getById(bid);
					delete(b);
					msg.setMsgtype(Msgtype.info);
					msg.setMsg("删除机构--"+b.getBranchName()+"--成功!");
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
