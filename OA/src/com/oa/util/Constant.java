package com.oa.util;

import org.apache.struts2.ServletActionContext;

public class Constant {
	
	//Ĭ�ϵ�ͷ���ļ��ϴ�·��
	public static final String UPLOAD_PATH="upload/";
	
	//Ĭ�ϵķ�ҳ��ʼ����
	public static final int PAGE_DEFAULT_INDEX=1;
	
	//Ĭ�ϵ�ҳ����ʾ��С
	public static final int PAGE_DEFAULT_SIZE=3;

	//Ĭ�ϵ�ҳü���к�
	public static final int PAGE_DEFAULT_HEADER=1;
	
	//��Ŀ����·��
	public static String PRO_PATH = System.getProperty("user.dir");
	
	//��Ŀ����
	public static String PRO_NAME = PRO_PATH.substring(PRO_PATH.lastIndexOf('\\')+1,PRO_PATH.length());

	//WEB����·��
	public static String WEB_PATH = ServletActionContext.getServletContext().getRealPath("");
	
	//WEB��Ŀ��
	public static String WEB_NAME = WEB_PATH.substring(WEB_PATH.lastIndexOf("\\")+1);
	
}
