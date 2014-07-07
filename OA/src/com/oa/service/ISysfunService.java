package com.oa.service;

import java.util.List;

import com.oa.entity.Sysfun;
import com.oa.entity.Userinfo;
import com.oa.util.Tree;

public interface ISysfunService extends IBaseService<Sysfun> {

	List<Tree> getAllTreeNode(Userinfo userinfo);

	List<Tree> getAllTreeNode(int roleId);

	List<Tree> getAllTreeNodeChecked(List<Tree> trees);

	String saveAuthorizeRole(String[] roleIds, int roleId);

	List<Sysfun> getAll(Userinfo userinfo);


}
