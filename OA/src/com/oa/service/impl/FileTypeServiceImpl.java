package com.oa.service.impl;


import com.oa.dao.IFiletypeinfoDao;
import com.oa.entity.Filetypeinfo;
import com.oa.service.IFileTypeService;

public class FileTypeServiceImpl extends BaseServiceImpl<Filetypeinfo>
		implements IFileTypeService {

	@Override
	public Filetypeinfo getPersistentBySuffix(Filetypeinfo f) {
		Filetypeinfo type = getFiletypeDao().findBySuffix(f.getFileTypeSuffix());
		if (type == null) {
			type = new Filetypeinfo(f.getFileTypeName(), f.getFileTypeSuffix(),
					f.getFileTypeName());
			getFiletypeDao().save(type);
		}
		return type;
	}
	
	public IFiletypeinfoDao getFiletypeDao(){
		return (IFiletypeinfoDao)getDao();
	}

}
