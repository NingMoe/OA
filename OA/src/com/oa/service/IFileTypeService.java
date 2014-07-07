package com.oa.service;

import com.oa.entity.Filetypeinfo;

public interface IFileTypeService extends IBaseService<Filetypeinfo>{
	
	/**
	 * 根据后缀名,获取文件类型持久化对象
	 * 存在则获取,否则新增
	 * @param f
	 * @return
	 */
	public Filetypeinfo getPersistentBySuffix(Filetypeinfo f);
	
	
}
