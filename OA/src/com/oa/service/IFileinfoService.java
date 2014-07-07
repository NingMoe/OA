package com.oa.service;

import java.util.List;
import java.util.Map;

import com.oa.commonModel.Json;
import com.oa.entity.Fileinfo;
import com.oa.util.UploadFile;

/**
 * �ļ���Ϣ��
 * ����������Ϊ���������,һ�����ĵ�,һ����ͷ��
 * @author Administrator
 *
 */
public interface IFileinfoService extends IBaseService<Fileinfo> {
	
	/**
	 * ��ȡ�ļ���
	 * @return
	 */
	@Deprecated
	public Fileinfo getRootDoc();
	
	/**
	 * ��ȡ�����ļ�
	 * @param fileinfo ƥ������
	 * @return
	 */
	@Deprecated
	public List<Fileinfo> getFiles(Fileinfo fileinfo);
	
	@Override
	public List getByExample(Fileinfo t);

	/**
	 * ������ѯ
	 * @param ��ѯ����
	 * @return
	 */
	public List<Fileinfo> getSearchByCriteria(Map<String,Object> m);
	
	/**
	 * ��ȡĿ¼�ڵ㼯��
	 * @return
	 */
	public List<Map<String,Object>> getDirNodes(List<Fileinfo> files);
	
	/**
	 * ��ȡ�ļ���Ϣ�ڵ㼯��
	 * @return
	 */
	public List<Map<String,Object>> getFileNodes(List<Fileinfo> files);
	
	/**
	 * �����ļ���ɾ��״̬
	 * @param fileId
	 * @param ifDelete ɾ��״̬
	 * @param falg �Ƿ�ɹ�
	 * @param msg ��ʾ��Ϣ
	 */
	public void updateFileState(Integer fileId,Integer ifDelete,Json j);
	
	/**
	 * �����ļ���ɾ��״̬
	 * @param fileId
	 * @param ifDelete ɾ��״̬
	 * @param falg �Ƿ�ɹ�
	 * @param msg ��ʾ��Ϣ
	 */
	public void updateAllFileState(Integer ifDelete,Json j);
	
	/**
	 * ��ݴ��ݵ�UploadFile����(�ļ�,�ļ���,�ļ���׺��,Ŀ��·��).
	 * �����ļ���Ϣ����ݿ�,
	 * �����ļ���ָ��·��.
	 */
	public Fileinfo save_fileinfo(UploadFile file);
	
	/**
	 * ����ͷ���ļ�,��Ϊ�����ļ�����
	 * @param file
	 */
	public void save_avatar(UploadFile file);
	
	public Fileinfo saveFile2DB(UploadFile file);
	
	/**
	 * �����ļ���Ŀ¼
	 * @param file
	 * @param finalPath t:����·�� f:���·�� 
	 */
	public void saveFile2Folder(UploadFile file,boolean finalPath);
	
	/**
	 * ����û�ID���ͷ��
	 */
	public Fileinfo getAvatar(String userId);
	
	/**
	 * �õ����е��ĵ�������(�ų�ͷ��)
	 * @return
	 */
	public List getFileOwners();
	
	public void delete(Fileinfo f,Json j);
	
	public void updatePids(Fileinfo f);
	
}
