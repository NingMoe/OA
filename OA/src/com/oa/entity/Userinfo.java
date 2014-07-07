package com.oa.entity;

import java.util.HashSet;
import java.util.Set;

import com.oa.util.FileTypeConstant;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */

public class Userinfo implements java.io.Serializable {

	// Fields

	private String userId;
	private Userstate userstate;
	private String userName;
	private String passWord;
	private Integer departId;
	private Departinfo departinfo;
	private Integer gender;
	private Set mynotes = new HashSet(0);
	private Set<Roleinfo> roleinfos = new HashSet<Roleinfo>(0);
	private Set<Precontract> precontracts = new HashSet<Precontract>(0);
	private Set readcommonmessages = new HashSet(0);
	private Set operatelogs = new HashSet(0);
	private Set messagetousers = new HashSet(0);
	private Set loginlogs = new HashSet(0);
	private Set manualsigns = new HashSet(0);
	/*private Set departinfos = new HashSet(0);*/
	private Set<Fileinfo> fileinfos = new HashSet<Fileinfo>(0);
	private Fileinfo avatarFile;

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** minimal constructor */
	public Userinfo(String userId) {
		this.setUserId(userId);
	}

	// /** minimal constructor */
	// public Userinfo(Userstate userstate, String userName, String passWord,
	// Integer departId, Integer gender) {
	// this.userstate = userstate;
	// this.userName = userName;
	// this.passWord = passWord;
	// this.departId = departId;
	// this.gender = gender;
	// }

	// /** full constructor */
	// public Userinfo(Userstate userstate, String userName, String passWord,
	// Integer departId, Integer gender, Set mynotes, Set roleinfos,
	// Set precontracts, Set readcommonmessages, Set operatelogs,
	// Set messagetousers, Set loginlogs, Set manualsigns,
	// Set departinfos, Set fileinfos) {
	// this.userstate = userstate;
	// this.userName = userName;
	// this.passWord = passWord;
	// this.departId = departId;
	// this.gender = gender;
	// this.mynotes = mynotes;
	// this.roleinfos = roleinfos;
	// this.setPrecontracts(precontracts);
	// this.readcommonmessages = readcommonmessages;
	// this.operatelogs = operatelogs;
	// this.messagetousers = messagetousers;
	// this.loginlogs = loginlogs;
	// this.manualsigns = manualsigns;
	// this.departinfos = departinfos;
	// this.fileinfos = fileinfos;
	// }

	public Userstate getUserstate() {
		return this.userstate;
	}

	public void setUserstate(Userstate userstate) {
		this.userstate = userstate;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getDepartId() {
		return this.departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Set getMynotes() {
		return this.mynotes;
	}

	public void setMynotes(Set mynotes) {
		this.mynotes = mynotes;
	}

	public Set<Roleinfo> getRoleinfos() {
		return this.roleinfos;
	}

	public void setRoleinfos(Set<Roleinfo> roleinfos) {
		this.roleinfos = roleinfos;
	}

	public Set getReadcommonmessages() {
		return this.readcommonmessages;
	}

	public void setReadcommonmessages(Set readcommonmessages) {
		this.readcommonmessages = readcommonmessages;
	}

	public Set getOperatelogs() {
		return this.operatelogs;
	}

	public void setOperatelogs(Set operatelogs) {
		this.operatelogs = operatelogs;
	}

	public Set getMessagetousers() {
		return this.messagetousers;
	}

	public void setMessagetousers(Set messagetousers) {
		this.messagetousers = messagetousers;
	}

	public Set getLoginlogs() {
		return this.loginlogs;
	}

	public void setLoginlogs(Set loginlogs) {
		this.loginlogs = loginlogs;
	}

	public Set getManualsigns() {
		return this.manualsigns;
	}

	public void setManualsigns(Set manualsigns) {
		this.manualsigns = manualsigns;
	}

//	public Set getDepartinfos() {
//		return this.departinfos;
//	}
//
//	public void setDepartinfos(Set departinfos) {
//		this.departinfos = departinfos;
//	}

	public Set<Precontract> getPrecontracts() {
		return precontracts;
	}

	public void setPrecontracts(Set<Precontract> precontracts) {
		this.precontracts = precontracts;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setAvatarFile(Fileinfo avatarFile) {
		this.avatarFile = avatarFile;
	}

	public Fileinfo getAvatarFile() {
		return avatarFile;
	}

	public Set<Fileinfo> getFileinfos() {
		return fileinfos;
	}

	public void setFileinfos(Set<Fileinfo> fileinfos) {
		this.fileinfos = fileinfos;
		for (Fileinfo fileinfo : fileinfos) {
			if (fileinfo.getFileId() != fileinfo.getParentId()
					&& fileinfo.getParentId() == FileTypeConstant.AVATAR_FOLDER_ID) {
				setAvatarFile(fileinfo);
			}
		}
	}

	public Departinfo getDepartinfo() {
		return departinfo;
	}

	public void setDepartinfo(Departinfo departinfo) {
		this.departinfo = departinfo;
		this.departId = departinfo.getDepartId();
	}
}