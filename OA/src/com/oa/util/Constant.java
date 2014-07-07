package com.oa.util;

import org.apache.struts2.ServletActionContext;

public class Constant {
	
	//默认的头像文件上传路径
	public static final String UPLOAD_PATH="upload/";
	
	//默认的分页起始索引
	public static final int PAGE_DEFAULT_INDEX=1;
	
	//默认的页面显示大小
	public static final int PAGE_DEFAULT_SIZE=3;

	//默认的页眉的行号
	public static final int PAGE_DEFAULT_HEADER=1;
	
	//项目所在路径
	public static String PRO_PATH = System.getProperty("user.dir");
	
	//项目名称
	public static String PRO_NAME = PRO_PATH.substring(PRO_PATH.lastIndexOf('\\')+1,PRO_PATH.length());

	//WEB所在路径
	public static String WEB_PATH = ServletActionContext.getServletContext().getRealPath("");
	
	//WEB项目名
	public static String WEB_NAME = WEB_PATH.substring(WEB_PATH.lastIndexOf("\\")+1);
	
}
