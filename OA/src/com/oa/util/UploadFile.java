package com.oa.util;

import java.io.File;

/**
 * 上传文件封装类
 * 
 * @author 蔡彬文
 * 
 */
public class UploadFile {

	// /////////////////// prop ////////////////////////
	private File upload;
	private String fileName;
	private String fileTypeName;
	private String filePath;
	private Object fileOwner;
	private Integer parentId;
	private String fileTypeSuffix;

	// ////////////////// construct method /////////////////////
	public UploadFile() {
	}

	public UploadFile(File upload, String fileName, String fileTypeName) {
		this.upload = upload;
		this.fileName = fileName;
		this.fileTypeName = fileTypeName;
	}

	public UploadFile(File upload, String fileName, String fileTypeName,
			String filePath) {
		this.upload = upload;
		this.fileName = fileName;
		this.fileTypeName = fileTypeName;
		this.filePath = filePath;
	}

	public UploadFile(File upload, String fileName, String fileTypeName,
			String filePath, Object fileOwner, Integer parentId,
			String fileTypeSuffix) {
		this.upload = upload;
		this.fileName = fileName;
		this.fileTypeName = fileTypeName;
		this.filePath = filePath;
		this.fileOwner = fileOwner;
		this.parentId = parentId;
		this.fileTypeSuffix = fileTypeSuffix;
	}

	// ////////////////// method /////////////////////
	/**
	 * 修改文件名
	 */
	public void fileRename(String currUserName) {
		setFileName(currUserName + getFileTypeSuffix());
	}
	
	// ////////////////// getter&setter /////////////////////
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		if (this.filePath == null) {
			setFilePath(Constant.UPLOAD_PATH + getFileName());
		}
		return this.filePath;
	}

	public void setFileOwner(Object fileOwner) {
		this.fileOwner = fileOwner;
	}

	public Object getFileOwner() {
		return fileOwner;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setFileTypeSuffix(String fileTypeSuffix) {
		this.fileTypeSuffix = fileTypeSuffix;
	}

	public String getFileTypeSuffix() {
		return fileTypeSuffix;
	}

}
