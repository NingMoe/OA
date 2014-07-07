package com.oa.action.impl;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oa.action.IFileinfoAction;
import com.oa.commonModel.Json;
import com.oa.entity.Fileinfo;
import com.oa.entity.Filetypeinfo;
import com.oa.entity.Userinfo;
import com.oa.service.IFileTypeService;
import com.oa.service.IFileinfoService;
import com.oa.util.FileTypeConstant;
import com.oa.util.MyFileHelper;
import com.oa.util.UploadFile;

public class FileinfoAction extends Action4easyui<Fileinfo> implements
		IFileinfoAction {

	private static final long serialVersionUID = 1L;

	// /////////////////////// prop /////////////////////////
	private IFileinfoService fileService;
	private IFileTypeService typeService;
	private List<Map<String, Object>> fileTree;
	private List<Map<String, Object>> dirTree;
	private Json json = new Json(false, "δ֪�쳣", null);
	private Fileinfo fileinfo;
	private List<Userinfo> fileOwners;
	private Map<String,Object> c=new HashMap<String,Object>();

	// �ļ��ϴ�
	private File file;
	private String fileContentType;
	private String fileFileName;
	private Userinfo userinfo;
	/*
	 * private Integer parentId; private String uploadPath;
	 */
	private Integer posFileId;// λ���ļ���ID
	private String fileTypeSuffix;

	// /////////////////////// method /////////////////////////
	@Override
	public UploadFile boxUp() {
		// ��ø��ļ�
		Fileinfo posFile = getFileService().getById(posFileId);

		// �������װ��
		// 1.�ϴ��ļ�
		File uploadFile = getFile();
		// 2.�ļ����
		String fileName = getFileFileName().substring(0,
				getFileFileName().lastIndexOf("."));
		// 3.�ļ���׺
		String fileTypeSuffix = getFileFileName().substring(
				getFileFileName().lastIndexOf("."));
		// 3.1.�ļ�������
		String fileTypeName = fileTypeSuffix.substring(1);
		// 4.�ļ����մ��·��
		String filePath = MyFileHelper.check_ends(posFile.getFilePath())
				+ getFileFileName();
		// 5.�ļ�������
		Userinfo fileOwner = (Userinfo) getSession().get("user");
		// 6.���ļ���ID
		Integer parentId = posFile.getFileId();

		UploadFile file = new UploadFile(uploadFile, fileName, fileTypeName,
				filePath, fileOwner, parentId, fileTypeSuffix);

		return file;
	}

	@Override
	public Fileinfo boxUp_folder() {
		if (getFileinfo() == null)
			return null;
		Integer pid = getPosFileId();
		Fileinfo p = getFileService().getById(pid);
		String folderName = MyFileHelper.getNameFromPath(getFileinfo()
				.getFileName()), path = (p != null) ? MyFileHelper.check_ends(p
				.getFilePath()) + folderName : folderName;

		Userinfo u = (Userinfo) getSession().get("user");
		String remark = getFileinfo().getRemark();
		Filetypeinfo type = getTypeService().getPersistentBySuffix(
				new Filetypeinfo(FileTypeConstant.FOLDER_TYPE_ID, "�ļ���", "",
						"folder"));

		Fileinfo file = new Fileinfo(u, type, folderName, remark,
				new Timestamp(new Date().getTime()), pid, path, 0);
		return file;
	}

	@Override
	public String show() {
		return null;
	}

	@Override
	public String update() {
		Fileinfo f = getFileService().getById(getFileinfo().getFileId());
		f.setRemark(getFileinfo().getRemark());
		getFileService().update(f);
		return SUCCESS;
	}

	@Override
	public String mkdir() {
		Fileinfo f = boxUp_folder();
		MyFileHelper.mkdirs(f.getFilePath(), false);
		getFileService().save(f);
		getFileService().updatePids(f);
		return SUCCESS;
	}

	@Override
	public String upload() {
		Fileinfo f = getFileService().save_fileinfo(boxUp());
		if(f!=null){
			getFileService().updatePids(f);
		}
		return NONE;
	}

	@Override
	public String uploadAvatar() {
		setPosFileId(com.oa.util.FileTypeConstant.AVATAR_FOLDER_ID);
		UploadFile f = boxUp();
		f.setFileOwner(getUserinfo());
		getFileService().save_avatar(f);
		return SUCCESS;
	}

	@Override
	public InputStream getInputStream() throws UnsupportedEncodingException {
		setFileinfo(getFileService().getById(getFileinfo().getFileId()));
		if (getFileinfo() == null) {
			getJson().setMsg("�ļ���Ϣ��¼������!");
			return null;
		}
		InputStream is = MyFileHelper.get_file_stream(getFileinfo()
				.getFilePath());
		if (is != null) {
			String n = new String(getFileinfo().getFileName()
					+ getFileinfo().getFiletypeinfo().getFileTypeSuffix());
			
			getFileinfo().setFileName(new String(n.getBytes(),"ISO-8859-1"));
			return is;
		}
		getJson().setMsg("�ļ�������!");
		return null;
	}

	@Override
	public String download() {
		return SUCCESS;
	}

	@Override
	public String putRecycle() {
		getFileService()
				.updateFileState(Integer.valueOf(getId()), 1, getJson());
		return SUCCESS;
	}

	@Override
	public String remove() {
		getFileService().delete(getFileinfo(), getJson());
		return SUCCESS;
	}

	@Override
	public String revert() {
		getFileService()
				.updateFileState(Integer.valueOf(getId()), 0, getJson());
		return SUCCESS;
	}

	@Override
	public String revertAll() {
		getFileService().updateAllFileState(1, getJson());
		return SUCCESS;
	}

	@Override
	public String showTree() {
		List l = getFileService().getFiles(new Fileinfo(null, 0));
		setDirTree(getFileService().getDirNodes(l));
		return SUCCESS;
	}

	/**
	 * ����һ��ƽ����,����Ϊ��Ŀ¼�ļ���(����)��,����δ���������վ���ļ���Ϣ.
	 */
	@Override
	public String showAll() {
		List l = getFileService().getFiles(new Fileinfo(null, 0));
		setFileTree(getFileService().getFileNodes(l));
		return SUCCESS;
	}

	@Override
	public String showRecycle() {
		List l = getFileService().getFiles(new Fileinfo(null, 1));
		setFileTree(getFileService().getFileNodes(l));
		return SUCCESS;
	}

	@Override
	public String showRecycleDir() {
		List l = getFileService().getFiles(new Fileinfo(null, 1));
		setDirTree(getFileService().getDirNodes(l));
		return SUCCESS;
	}
	
	@Override
	public String fileOwners() {
		setFileOwners(getFileService().getFileOwners());
		return SUCCESS;
	}
	
	@Override
	public String doSearch() {
		/*List l = getFileService().getByExample(getFileinfo());*/
		/*c.put("queryName", "queryName");*/
		/*List<?> l = getBaseService().getByNamedQuery(c);*/
		List<Fileinfo> l = getFileService().getSearchByCriteria(c);
		setFileTree(getFileService().getFileNodes(l));
		return SUCCESS;
	}

	@Override
	/*
	 * String[] properties = { "remark", "filetypeinfo.fileTypeName",
	 * "userinfo.userName", "createDate" }; getFileTree().add(buildTree(root,
	 * properties, false));
	 */
	@Deprecated
	public Map<String, Object> buildTree(Fileinfo f, String[] extraProps,
			boolean isText) {
		Map<String, Object> node = new HashMap<String, Object>();
		node.put("id", f.getFileId().toString());
		node.put(isText ? "text" : "name", f.getFileName());
		node.put("iconCls", f.getFiletypeinfo().getFileTypeImage());

		if (extraProps != null && extraProps.length > 0) {
			node.putAll(reflectGetPropVal(f, extraProps));
		}

		Object[] childs = f.getChildFiles().toArray();
		if (childs.length > 0) {
			List<Map<String, Object>> nodes = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < childs.length; i++) {
				Fileinfo fileinfo = (Fileinfo) childs[i];
				if (fileinfo.getFileId() == f.getFileId())
					continue;
				nodes.add(buildTree(fileinfo, extraProps, isText));
			}
			node.put("children", nodes);
		}
		return node;
	}

	@Override
	@Deprecated
	public Map<String, Object> reflectGetPropVal(Fileinfo f, String[] props) {
		Map<String, Object> propVal = new HashMap<String, Object>();
		for (String prop : props) {
			try {
				prop = prop.trim();
				if (prop.startsWith(".") || prop.endsWith(".")) {
					System.err.println("������Ч,������'.'��ͷ���β!");
					continue;
				} else if (prop.contains(".")) {
					String[] objDotProp = prop.split("\\.");
					int i = 0;
					Class clazz = f.getClass();
					Object value = null;
					int length = objDotProp.length;
					while (i < length) {
						value = clazz.getMethod(getGetterName(objDotProp[i]),
								null).invoke(value == null ? f : value, null);
						clazz = clazz.getMethod(getGetterName(objDotProp[i]),
								null).getReturnType();
						i++;
					}
					propVal.put(prop, value);
				} else {
					Object value = f.getClass()
							.getMethod(getGetterName(prop), null)
							.invoke(f, null);
					propVal.put(prop, value);
				}

			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return propVal;
	}

	@Override
	@Deprecated
	public String getGetterName(String propName) {
		return "get" + propName.substring(0, 1).toUpperCase()
				+ propName.substring(1);
	}

	// /////////////////////// getter&setter /////////////////////////
	public void setFileTree(List<Map<String, Object>> fileTree) {
		this.fileTree = fileTree;
	}

	public List<Map<String, Object>> getFileTree() {
		return fileTree;
	}

	public void setDirTree(List<Map<String, Object>> dirTree) {
		this.dirTree = dirTree;
	}

	public List<Map<String, Object>> getDirTree() {
		return dirTree;
	}

	public void setJson(Json json) {
		this.json = json;
	}

	public Json getJson() {
		return json;
	}

	public void setFileinfo(Fileinfo fileinfo) {
		this.fileinfo = fileinfo;
	}

	public Fileinfo getFileinfo() {
		return fileinfo;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setFileTypeSuffix(String fileTypeSuffix) {
		this.fileTypeSuffix = fileTypeSuffix;
	}

	public String getFileTypeSuffix() {
		return fileTypeSuffix;
	}

	public void setPosFileId(Integer posFileId) {
		if (posFileId == null || posFileId == 0) {
			this.posFileId = FileTypeConstant.ROOT_FOLDER;
		} else {
			this.posFileId = posFileId;
		}
	}

	public Integer getPosFileId() {
		return posFileId;
	}

	public void setFileService(IFileinfoService fileService) {
		this.fileService = fileService;
	}

	public IFileinfoService getFileService() {
		return fileService;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setTypeService(IFileTypeService typeService) {
		this.typeService = typeService;
	}

	public IFileTypeService getTypeService() {
		return typeService;
	}

	public void setFileOwners(List<Userinfo> fileOwners) {
		this.fileOwners = fileOwners;
	}

	public List<Userinfo> getFileOwners() {
		return fileOwners;
	}

	public Map<String, Object> getC() {
		return c;
	}

	public void setC(Map<String, Object> c) {
		this.c = c;
	}
	
}