package com.oa.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.oa.entity.Userinfo;

public interface IUserService extends IBaseService<Userinfo> {

	public boolean login(Userinfo userinfo);

	/**
	 * 将接受用户提交数据的简单对象,填充为完整对象
	 */
	public Userinfo simple2FullObj(Userinfo user);

	/**
	 * 查询指定POJO集合并返回.
	 * 
	 * @param list
	 * @return
	 */
	public List<?> getOtherObjList(Class clazz);

	/**
	 * 插入并关联用户头像
	 */
	/* public void relatingIcon(UploadFile uploadFile,Userinfo user); */

	/**
	 * 保存文件类型和文件到数据库
	 * 
	 * @param uploadFile
	 */
	/* public void saveFile2DB(UploadFile uploadFile,Userinfo userinfo); */

	/**
	 * 保存图片到文件夹
	 * 
	 * @param 上传文件
	 * @param 保存文件的路径
	 */
	/* public void saveFile2Folder(UploadFile iconFile) ; */

	/**
	 * 保存文件类型
	 * 
	 * @param uploadFile
	 */
	/* public Filetypeinfo saveFiletypeinfo(UploadFile uploadFile); */

	/**
	 * 保存文件信息到数据库
	 */
	/*
	 * public void saveFileinfo(UploadFile uploadFile,Userinfo
	 * userinfo,Filetypeinfo typeinfo);
	 */

	/**
	 * 根据类型和ID,查询其他POJO记录
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public Object getOtherObjById(Class clazz, Serializable id);

	/**
	 * 修改用户密码
	 * 
	 * @param 原用户
	 * @param 新密码
	 * @return 原密码是否正确
	 */
	public boolean updatePwd(Userinfo user, String newPwd);

	/**
	 * 验证用户Id是否已存在
	 * 
	 * @param userId
	 * @return
	 */
	public boolean userId_isExists(String userId);

	public List<Userinfo> getObjByMap(Map<String, Object> params);

}
