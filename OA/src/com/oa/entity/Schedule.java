package com.oa.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Schedule entity. @author MyEclipse Persistence Tools
 */

public class Schedule implements java.io.Serializable {

	private Integer scheduleId;
	private Meetinginfo meetinginfo;
	private String title;
	private String address;
	private Timestamp beginTime;
	private Timestamp endTime;
	private String schContent;
	private String createUser;
	private Timestamp createTime;
	private String ifPrivate;
	private Set precontracts = new HashSet(0);

	// Constructors

	/** default constructor */
	public Schedule() {
	}

	/** minimal constructor */
	public Schedule(Meetinginfo meetinginfo, String title, String address,
			Timestamp beginTime, Timestamp endTime, String schContent,
			String createUser, Timestamp createTime, String ifPrivate) {
		this.meetinginfo = meetinginfo;
		this.title = title;
		this.address = address;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.schContent = schContent;
		this.createUser = createUser;
		this.createTime = createTime;
		this.ifPrivate = ifPrivate;
	}

	/** full constructor */
	public Schedule(Meetinginfo meetinginfo, String title, String address,
			Timestamp beginTime, Timestamp endTime, String schContent,
			String createUser, Timestamp createTime, String ifPrivate,
			Set precontracts) {
		this.meetinginfo = meetinginfo;
		this.title = title;
		this.address = address;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.schContent = schContent;
		this.createUser = createUser;
		this.createTime = createTime;
		this.ifPrivate = ifPrivate;
		this.precontracts = precontracts;
	}

	// Property accessors

	public Integer getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Meetinginfo getMeetinginfo() {
		return this.meetinginfo;
	}

	public void setMeetinginfo(Meetinginfo meetinginfo) {
		this.meetinginfo = meetinginfo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getSchContent() {
		return this.schContent;
	}

	public void setSchContent(String schContent) {
		this.schContent = schContent;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getIfPrivate() {
		return this.ifPrivate;
	}

	public void setIfPrivate(String ifPrivate) {
		this.ifPrivate = ifPrivate;
	}

	public Set getPrecontracts() {
		return this.precontracts;
	}

	public void setPrecontracts(Set precontracts) {
		this.precontracts = precontracts;
	}

}