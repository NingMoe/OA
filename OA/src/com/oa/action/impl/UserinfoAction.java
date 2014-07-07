package com.oa.action.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.ApplicationAware;
import org.springframework.beans.BeanUtils;

import com.oa.action.IUserAction;
import com.oa.entity.Departinfo;
import com.oa.entity.Loginlog;
import com.oa.entity.Roleinfo;
import com.oa.entity.Userinfo;
import com.oa.entity.Userstate;
import com.oa.service.IDepartService;
import com.oa.service.ILogService;
import com.oa.service.impl.UserinfoServiceImpl;
import com.oa.util.MyFileHelper;
import com.oa.util.UploadFile;

public class UserinfoAction extends Action4easyui<Userinfo> implements
		IUserAction,ApplicationAware {

	private static final long serialVersionUID = 1L;

	/** 属性 */
	private Userinfo userinfo;
	private int[] roleIds;

	private Loginlog loginlog;
	private List<Departinfo> departinfos = new ArrayList<Departinfo>();
	private List<Userstate> userStates = new ArrayList<Userstate>();
	private List<Roleinfo> roleinfos = new ArrayList<Roleinfo>();
	private ILogService logService;
	private IDepartService departService;
	private boolean success;// 用户是否登录成功
	private String newPwd;//
	private String msg;// 返回给客户端的消息
	private Map<String,Object> application;
	
	private File file;
	private String fileContentType;
	private String fileFileName;

	///////////////////////////// method /////////////////////////////////
	/**
	 * 把用户存入session
	 */
	public void putUserInSession(Userinfo user) {
		Userinfo sessionUser = new Userinfo();// 剔除用户密码
		BeanUtils
				.copyProperties(user, sessionUser, new String[] { "passWord" });
		getSession().put("user", sessionUser);
		
		//把session存入自定义容器,防止flash session丢失
		/*MyTempSession.AddSession(getSession());*/
	}

	public String ajaxLogin() {
		boolean flag = ((UserinfoServiceImpl) getBaseService()).login(userinfo);
		if (flag) {
			setSuccess(true);

			putUserInSession(getUserinfo());
			getSession().put(
					"departName",
					getDepartService().getById(getUserinfo().getDepartId())
							.getDepartName());
		} else {
			setSuccess(false);
		}

		return "root";
	}

	@Override
	public String modifyPwd() {
		try {
			if (getRealityService().updatePwd(getUserinfo(), getNewPwd())) {
				setMsg("密码修改成功!");
			} else
				setMsg("原密码不正确!");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			setMsg("修改失败!");
		}
		return SUCCESS;
	}

	@Override
	public String logout() {
		getSession().remove("user");
		/*MyTempSession.clear();//清理自定义session容器*/
		return NONE;
	}

	@Override
	public String nameExists() {
		boolean isExists = getRealityService().userId_isExists(
				getUserinfo().getUserId());
		if(!isExists){
			setSuccess(true);
		}else setSuccess(false);
		return SUCCESS;
	}
	
	@Override
	public String remove() {
		Userinfo u = getBaseService().getById(getId());
		getBaseService().delete(u);
		return SUCCESS;
	}

	@Override
	public String addOrUpdate() {

		// 用户数据
		getBaseService().saveOrUpdate(
				getRealityService().simple2FullObj(getUserinfo()));
		
		// 如果用户上传了头像,就继续转发到FileAction
		if (getRequest().get("file") != null) {
			
			String newname = MyFileHelper.fileRename(getFileFileName(), ((Userinfo)getSession().get("user")).getUserName());
			setFileFileName(newname);
			return "chain";			
		}
		
		return SUCCESS;
	}

	@Override
	public String show() {
		setUserinfo(getRealityService().getById(getUserinfo().getUserId()));
		return SUCCESS;
	}

	@Override
	public String showAllDepart() {
		setActionProp(Departinfo.class, departinfos);
		return SUCCESS;
	}

	@Override
	public String showAllRole() {
		setActionProp(Roleinfo.class, roleinfos);
		return SUCCESS;
	}

	@Override
	public String showAllState() {
		setActionProp(Userstate.class, userStates);
		return SUCCESS;
	}
	
	/**
	 * 经过强转的服务实际类
	 */
	@Override
	public UserinfoServiceImpl getRealityService() {
		return (UserinfoServiceImpl) getBaseService();
	}

	/**
	 * 将从后台获取的集合数据装入到此Action类属性
	 * 
	 * @param clazz
	 *            POJO类型,据此查询集合
	 * @param list
	 *            存入的集合属性(必须经过实例化)
	 */
	@Override
	public void setActionProp(Class<?> clazz, List list) {
		if (clazz == null || list == null)
			return;
		list.clear();
		list.addAll(getRealityService().getOtherObjList(clazz));
	}

	// ////////////////////////////////getter&setter//////////////////////////////////
	@Override
	public Userinfo getPojo() {
		return super.getPojo();
	}

	@Override
	public void setPojo(Userinfo pojo) {
		super.setPojo(pojo);
	}

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	/**
	 * 解决jsp传递set集合中值获取不到的问题~! 单独设立属性接收,而后自动动进行组装.
	 * 
	 * @param roleId
	 */
	public void setRoleIds(int[] roleIds) {
		this.roleIds = roleIds;
		Set<Roleinfo> roles = new HashSet<Roleinfo>();
		for (int roleId : roleIds) {
			roles.add(new Roleinfo(roleId));
		}
		getUserinfo().setRoleinfos(roles);
	}

	public int[] getRoleIds() {
		return roleIds;
	}

	public List<Departinfo> getDepartinfos() {
		return departinfos;
	}

	public void setDepartinfos(List<Departinfo> departinfos) {
		this.departinfos = departinfos;
	}

	public List<Userstate> getUserStates() {
		return userStates;
	}

	public void setUserStates(List<Userstate> userStates) {
		this.userStates = userStates;
	}

	public List<Roleinfo> getRoleinfos() {
		return roleinfos;
	}

	public void setRoleinfos(List<Roleinfo> roleinfos) {
		this.roleinfos = roleinfos;
	}

	public Loginlog getLoginlog() {
		return loginlog;
	}

	public void setLoginlog(Loginlog loginlog) {
		this.loginlog = loginlog;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}

	public ILogService getLogService() {
		return logService;
	}

	public void setDepartService(IDepartService departService) {
		this.departService = departService;
	}

	public IDepartService getDepartService() {
		return departService;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	@Override
	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;
	}
	
	public Map<String, Object> getApplication(){
		return this.application;
	}
	
	

}
