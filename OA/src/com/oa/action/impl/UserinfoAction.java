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

	/** ���� */
	private Userinfo userinfo;
	private int[] roleIds;

	private Loginlog loginlog;
	private List<Departinfo> departinfos = new ArrayList<Departinfo>();
	private List<Userstate> userStates = new ArrayList<Userstate>();
	private List<Roleinfo> roleinfos = new ArrayList<Roleinfo>();
	private ILogService logService;
	private IDepartService departService;
	private boolean success;// �û��Ƿ��¼�ɹ�
	private String newPwd;//
	private String msg;// ���ظ��ͻ��˵���Ϣ
	private Map<String,Object> application;
	
	private File file;
	private String fileContentType;
	private String fileFileName;

	///////////////////////////// method /////////////////////////////////
	/**
	 * ���û�����session
	 */
	public void putUserInSession(Userinfo user) {
		Userinfo sessionUser = new Userinfo();// �޳��û�����
		BeanUtils
				.copyProperties(user, sessionUser, new String[] { "passWord" });
		getSession().put("user", sessionUser);
		
		//��session�����Զ�������,��ֹflash session��ʧ
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
				setMsg("�����޸ĳɹ�!");
			} else
				setMsg("ԭ���벻��ȷ!");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			setMsg("�޸�ʧ��!");
		}
		return SUCCESS;
	}

	@Override
	public String logout() {
		getSession().remove("user");
		/*MyTempSession.clear();//�����Զ���session����*/
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

		// �û�����
		getBaseService().saveOrUpdate(
				getRealityService().simple2FullObj(getUserinfo()));
		
		// ����û��ϴ���ͷ��,�ͼ���ת����FileAction
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
	 * ����ǿת�ķ���ʵ����
	 */
	@Override
	public UserinfoServiceImpl getRealityService() {
		return (UserinfoServiceImpl) getBaseService();
	}

	/**
	 * ���Ӻ�̨��ȡ�ļ�������װ�뵽��Action������
	 * 
	 * @param clazz
	 *            POJO����,�ݴ˲�ѯ����
	 * @param list
	 *            ����ļ�������(���뾭��ʵ����)
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
	 * ���jsp����set������ֵ��ȡ����������~! �����������Խ���,�����Զ���������װ.
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
