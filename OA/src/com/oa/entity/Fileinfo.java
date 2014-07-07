package com.oa.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Fileinfo entity. @author MyEclipse Persistence Tools
 */

public class Fileinfo implements java.io.Serializable {

	// Fields
	private Integer fileId;
	private Userinfo userinfo;
	private Filetypeinfo filetypeinfo;
	private String fileName;
	private String remark;
	private Timestamp createDate;
	private Integer parentId;
	private String filePath;
	private Integer ifDelete;
	private Set accessoryfiles = new HashSet(0);
	private Set<Fileinfo> childFiles = new HashSet<Fileinfo>(0);
	private String pids;

	// Constructors

	/** default constructor */
	public Fileinfo() {
	}

	/** minimal constructor */
	public Fileinfo(Integer fileId, Integer ifDelete) {
		this.fileId = fileId;
		this.ifDelete = ifDelete;
	}
	
	/** minimal constructor */
	public Fileinfo(String fileName,Filetypeinfo type,Integer pid) {
		this.fileName = fileName;
		this.filetypeinfo = type;
		this.parentId = pid;
	}

	/** minimal constructor */
	public Fileinfo(Integer fileId) {
		this.fileId = fileId;
	}

	/** minimal constructor */
	public Fileinfo(Userinfo userinfo, Filetypeinfo filetypeinfo,
			String fileName, String remark, Timestamp createDate,
			Integer parentId, String filePath, Integer ifDelete) {
		this.userinfo = userinfo;
		this.filetypeinfo = filetypeinfo;
		this.fileName = fileName;
		this.createDate = createDate;
		this.parentId = parentId;
		this.filePath = filePath;
		this.ifDelete = ifDelete;
		this.remark = remark;
	}
	
	/** minimal constructor */
	public Fileinfo(Userinfo userinfo, Filetypeinfo filetypeinfo,
			String fileName, String remark, Timestamp createDate,
			Integer parentId, String filePath, Integer ifDelete,String pids) {
		this.userinfo = userinfo;
		this.filetypeinfo = filetypeinfo;
		this.fileName = fileName;
		this.createDate = createDate;
		this.parentId = parentId;
		this.filePath = filePath;
		this.ifDelete = ifDelete;
		this.remark = remark;
		this.pids = pids;
	}

	/** full constructor */
	public Fileinfo(Userinfo userinfo, Filetypeinfo filetypeinfo,
			String fileName, String remark, Timestamp createDate,
			Integer parentId, String filePath, Integer ifDelete,
			Set accessoryfiles) {
		this.userinfo = userinfo;
		this.filetypeinfo = filetypeinfo;
		this.fileName = fileName;
		this.remark = remark;
		this.createDate = createDate;
		this.parentId = parentId;
		this.filePath = filePath;
		this.ifDelete = ifDelete;
		this.accessoryfiles = accessoryfiles;
	}

	// Property accessors

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public Filetypeinfo getFiletypeinfo() {
		return this.filetypeinfo;
	}

	public void setFiletypeinfo(Filetypeinfo filetypeinfo) {
		this.filetypeinfo = filetypeinfo;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getIfDelete() {
		return this.ifDelete;
	}

	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}

	public Set getAccessoryfiles() {
		return this.accessoryfiles;
	}

	public void setAccessoryfiles(Set accessoryfiles) {
		this.accessoryfiles = accessoryfiles;
	}

	public void setChildFiles(Set<Fileinfo> childFiles) {
		this.childFiles = childFiles;
	}

	public Set<Fileinfo> getChildFiles() {
		return childFiles;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public String getPids() {
		return pids;
	}

}