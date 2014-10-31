package com.oa.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.oa.entity.Userinfo;

public interface IUserService extends IBaseService<Userinfo> {

	public boolean login(Userinfo userinfo);

	/**
	 * �������û��ύ���ݵļ򵥶���,���Ϊ��������
	 */
	public Userinfo simple2FullObj(Userinfo user);

	/**
	 * ��ѯָ��POJO���ϲ�����.
	 * 
	 * @param list
	 * @return
	 */
	public List<?> getOtherObjList(Class clazz);

	/**
	 * ���벢�����û�ͷ��
	 */
	/* public void relatingIcon(UploadFile uploadFile,Userinfo user); */

	/**
	 * �����ļ����ͺ��ļ������ݿ�
	 * 
	 * @param uploadFile
	 */
	/* public void saveFile2DB(UploadFile uploadFile,Userinfo userinfo); */

	/**
	 * ����ͼƬ���ļ���
	 * 
	 * @param �ϴ��ļ�
	 * @param �����ļ���·��
	 */
	/* public void saveFile2Folder(UploadFile iconFile) ; */

	/**
	 * �����ļ�����
	 * 
	 * @param uploadFile
	 */
	/* public Filetypeinfo saveFiletypeinfo(UploadFile uploadFile); */

	/**
	 * �����ļ���Ϣ�����ݿ�
	 */
	/*
	 * public void saveFileinfo(UploadFile uploadFile,Userinfo
	 * userinfo,Filetypeinfo typeinfo);
	 */

	/**
	 * �������ͺ�ID,��ѯ����POJO��¼
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public Object getOtherObjById(Class clazz, Serializable id);

	/**
	 * �޸��û�����
	 * 
	 * @param ԭ�û�
	 * @param ������
	 * @return ԭ�����Ƿ���ȷ
	 */
	public boolean updatePwd(Userinfo user, String newPwd);

	/**
	 * ��֤�û�Id�Ƿ��Ѵ���
	 * 
	 * @param userId
	 * @return
	 */
	public boolean userId_isExists(String userId);

	public List<Userinfo> getObjByMap(Map<String, Object> params);

}
