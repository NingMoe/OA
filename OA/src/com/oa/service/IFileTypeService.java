package com.oa.service;

import com.oa.entity.Filetypeinfo;

public interface IFileTypeService extends IBaseService<Filetypeinfo>{
	
	/**
	 * ���ݺ�׺��,��ȡ�ļ����ͳ־û�����
	 * �������ȡ,��������
	 * @param f
	 * @return
	 */
	public Filetypeinfo getPersistentBySuffix(Filetypeinfo f);
	
	
}
