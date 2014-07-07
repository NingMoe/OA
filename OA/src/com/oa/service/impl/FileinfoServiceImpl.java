package com.oa.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import com.oa.commonModel.Json;
import com.oa.dao.IFileinfoDao;
import com.oa.entity.Fileinfo;
import com.oa.entity.Filetypeinfo;
import com.oa.entity.Userinfo;
import com.oa.service.IFileTypeService;
import com.oa.service.IFileinfoService;
import com.oa.util.Constant;
import com.oa.util.FileTypeConstant;
import com.oa.util.ImageUtils;
import com.oa.util.MyFileHelper;
import com.oa.util.UploadFile;

public class FileinfoServiceImpl extends BaseServiceImpl<Fileinfo> implements
		IFileinfoService {

	private IFileTypeService filetypeService;

	@Override
	@Deprecated
	public Fileinfo getRootDoc() {
		return (Fileinfo) getDao().findById(Fileinfo.class,
				FileTypeConstant.ROOT_FOLDER);
	}

	/**
	 * ��ȡ�ļ�. ���Ŀ¼�ļ���(����)��,����δ���������վ���ļ�.
	 */
	@Override
	@Deprecated
	public List<Fileinfo> getFiles(Fileinfo fileinfo) {
		return getDao()
				.getCurrSession()
				.createCriteria(Fileinfo.class)
				.add(Example.create(fileinfo))
				.add(Restrictions.not(Restrictions.eq("id",
						FileTypeConstant.ROOT_FOLDER)))
				.add(Restrictions.not(Restrictions.eq("parentId",
						FileTypeConstant.AVATAR_FOLDER_ID))).list();
	}

	@Override
	public List getByExample(Fileinfo t) {
		return getFiles(t);
	}
	
	@Override
	public List<Map<String, Object>> getDirNodes(List<Fileinfo> files) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Fileinfo f : files) {
			Map<String, Object> node = new HashMap<String, Object>();
			node.put("id", f.getFileId());
			node.put("text", f.getFileName());
			node.put("pid", f.getParentId().toString());
			node.put("iconCls", f.getFiletypeinfo().getFileTypeImage());
			list.add(node);
		}
		return list;
	}

	@Override
	public void updatePids(Fileinfo f) {
		if(f.getFileId()!=f.getParentId()){
			f.setPids(getById(f.getParentId()).getPids()+f.getFileId()+",");
		}else{
			f.setPids(f.getFileId()+",");
		}
		update(f);
	}
	
	@Override
	public List<Map<String, Object>> getFileNodes(List<Fileinfo> files) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Fileinfo f : files) {
			Map<String, Object> node = new HashMap<String, Object>();
			node.put("id", f.getFileId());
			node.put("name", f.getFileName());
			node.put("pid", f.getParentId().toString());
			node.put("iconCls", f.getFiletypeinfo().getFileTypeImage());
			node.put("filetypeinfo.fileTypeName", f.getFiletypeinfo()
					.getFileTypeName());
			node.put("suffix", f.getFiletypeinfo().getFileTypeSuffix());
			node.put("filePath", f.getFilePath());
			node.put("remark", f.getRemark());
			node.put("userinfo.userName", f.getUserinfo().getUserName());
			node.put("createDate", new Date(f.getCreateDate().getTime()));
			boolean folder = f.getFiletypeinfo().getFileTypeId().intValue() == FileTypeConstant.FOLDER_TYPE_ID.intValue();
			if (folder) {
				node.put("folderPath", f.getFilePath());
			} else {
				String path = f.getFilePath();
				node.put("folderPath",
						path.substring(0, path.lastIndexOf("/") + 1));
			}

			list.add(node);
		}
		return list;
	}

	@Override
	public void updateFileState(Integer fileId, Integer ifDelete, Json j) {

		Fileinfo f = getById(fileId);
		if (f == null) {
			j.setMsg("���ļ��Ѳ�����!");
		} else {
			// �ݹ�
			recursionErgodic(f, ifDelete);
			j.setSuccess(true);
			j.setMsg("�������!");
		}
	}

	/**
	 * �ݹ����,���¶���
	 */
	public void recursionErgodic(Fileinfo f, Integer ifDelete) {
		f.setIfDelete(ifDelete);
		Object[] childs = f.getChildFiles().toArray();
		if (childs.length > 0) {
			for (int i = 0; i < childs.length; i++) {
				Fileinfo fileinfo = (Fileinfo) childs[i];
				if (fileinfo.getFileId() == f.getFileId())
					continue;

				fileinfo.setIfDelete(ifDelete);
				recursionErgodic(fileinfo, ifDelete);
			}
		}
	}

	@Override
	public void updateAllFileState(Integer ifDelete, Json j) {
		Query q = getDao().getCurrSession().createQuery(
				"update Fileinfo f set f.ifDelete=?" + " where f.ifDelete=?");
		q.setInteger(0, (ifDelete == 0 ? 1 : 0));
		q.setInteger(1, ifDelete);

		if (q.executeUpdate() > 0) {
			j.setSuccess(true);
			j.setMsg("����ȫ���ļ�״̬�ɹ�!");
		}
	}

	@Override
	public Fileinfo save_fileinfo(UploadFile file) {
		saveFile2Folder(file, false);
		return saveFile2DB(file);
	}

	@Override
	public void save_avatar(UploadFile f) {

		ImageUtils.pressText(f.getUpload().getAbsolutePath(),
				"Office Automation", "��Բ", Font.BOLD | Font.ITALIC, 20,
				Color.pink, 10, 10, 0.8f);

		Fileinfo file = getAvatar(((Userinfo) f.getFileOwner()).getUserId());
		if (file == null) {
			saveFile2DB(f);
		} else {
			file.setCreateDate(new Timestamp(new Date().getTime()));
			file.setFileName(f.getFileName());
			file.setFilePath(f.getFilePath());
			update(file);
		}

		f.setFilePath(MyFileHelper.check_ends(Constant.WEB_PATH)
				+ f.getFilePath());
		saveFile2Folder(f, true);
	}

	@Override
	public Fileinfo saveFile2DB(UploadFile f) {

		Filetypeinfo type = new Filetypeinfo(f.getFileTypeName(),
				f.getFileTypeSuffix(), f.getFileTypeName());
		type = getFiletypeService().getPersistentBySuffix(type);

		// �����ڸ��ļ���¼,��ֱ�Ӹ���
		List<Fileinfo> list = null;
		try {
			list = getDao().findByExample(
					new Fileinfo(f.getFileName(), type, f.getParentId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list != null && !list.isEmpty()) {
			Fileinfo ff = list.get(0);
			ff.setCreateDate(new Timestamp(new Date().getTime()));
			ff.setUserinfo((Userinfo) f.getFileOwner());
			getDao().update(ff);
			return null;
		}

		Fileinfo fileinfo = new Fileinfo((Userinfo) f.getFileOwner(), type,
				f.getFileName(), "", new Timestamp(new Date().getTime()),
				f.getParentId(), f.getFilePath(), 0);

		getDao().save(fileinfo);
		return fileinfo;
	}

	@Override
	public void saveFile2Folder(UploadFile f, boolean finalPath) {
		MyFileHelper.put_file(f.getUpload(), f.getFilePath(), finalPath);
	}

	@Override
	public Fileinfo getAvatar(String userId) {
		return ((IFileinfoDao) getDao()).findAvatar(userId);
	}

	@Override
	public void delete(Fileinfo f, Json j) {
		f = (Fileinfo) getDao().findById(f.getClass(), f.getFileId());
		if (f == null) {
			j.setSuccess(false);
			j.setMsg("ɾ��ʧ��,��ݿ��Ѳ����ڸü�¼!");
			return;
		}
		getDao().delete(f);
		if (MyFileHelper.delete(f.getFilePath())) {
			j.setSuccess(true);
			j.setMsg("ɾ��ɹ�!");
		} else {
			j.setSuccess(false);
			j.setMsg("�����쳣,�ļ������ڻ������ļ������쳣!");
		}
	}

	@Override
	public List getFileOwners() {
		String hql = "SELECT u FROM Userinfo u where u.userId in (SELECT distinct f.userinfo FROM Fileinfo f WHERE f.parentId <> "
				+ FileTypeConstant.AVATAR_FOLDER_ID + ")";
		return getDao().findByHql(hql);
	}
	
	@Override
	public List<Fileinfo> getSearchByCriteria(Map<String, Object> m) {
		try {
			IFileinfoDao fDao = (IFileinfoDao)getDao();
			List<Fileinfo> l = fDao.findByCriteria(m);
			return l;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setFiletypeService(IFileTypeService filetypeService) {
		this.filetypeService = filetypeService;
	}

	public IFileTypeService getFiletypeService() {
		return filetypeService;
	}

}
