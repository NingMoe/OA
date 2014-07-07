package com.oa.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.oa.entity.Fileinfo;
import com.oa.util.UploadFile;

public interface IFileinfoAction extends IBaseAction {
	
	public String showTree();
	
	@Override
	public String showAll();
	
	public String showRecycleDir();
	
	public String showRecycle();
	
	/**
	 * �������վ
	 * @return
	 */
	public String putRecycle();

	/**
	 * �����ļ�Ŀ¼(�ļ���)
	 * @return
	 */
	public String mkdir();
	
	/**
	 * ��ԭ
	 * @return
	 */
	public String revert();	
	
	/**
	 * ȫ����ԭ
	 * @return
	 */
	public String revertAll();	
	
	/**
	 * �ϴ��ĵ�
	 * @return
	 */
	public String upload();
	
	/**
	 * �ϴ��û�ͷ��
	 * @return
	 */
	public String uploadAvatar();
	
	public InputStream getInputStream() throws UnsupportedEncodingException;
	
	/**
	 * �����ĵ�
	 * @return
	 */
	public String download();
	
	/**
	 * ����ݴ��(��һ���ļ�)
	 */
	public UploadFile boxUp();
	
	/**
	 * ����ݴ��(���ļ���)
	 */
	public Fileinfo boxUp_folder();
	
	/**
	 * ��ʾ�����ļ�������
	 * @return
	 */
	public String fileOwners();
	
	/**
	 * �����ļ�
	 * @return
	 */
	public String doSearch();
	
	/**
	 * �����ļ���
	 * @param f ����
	 * @param extraProps ������ӵ�����
	 * @param isText �������Ƿ���'text':'value'����,����'name':'value'
	 * @return ���ڵ�
	 */
	@Deprecated
	public Map<String,Object> buildTree(Fileinfo f,String[] extraProps,boolean isText);
	/**
	 * ��ݴ��ݵ����Ա��ʽ(�ɺ�.),�Ӷ����з����ȡֵ
	 * @param f ��ֵ����
	 * @param props ��������
	 * @return
	 */
	@Deprecated
	public Map<String,Object> reflectGetPropVal(Fileinfo f,String[] props);
	/**
	 * ͨ����������,���getter������
	 * @param propName
	 * @return
	 */
	@Deprecated
	public String getGetterName(String propName);
}
