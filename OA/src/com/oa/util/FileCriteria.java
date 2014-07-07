package com.oa.util;

import java.sql.Timestamp;

/**
 * 为搜索文件时产生的数据表单,而建立的类
 * @author Administrator
 *
 */
public class FileCriteria {

	private String name;
	private String remark;
	private Integer scopeId;
	private Integer dayAgo;
	private Integer monAgo;
	private Timestamp beginDate;
	private Timestamp endDate;
	private Integer typeId;
	private Integer userId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getScopeId() {
		return scopeId;
	}
	public void setScopeId(Integer scopeId) {
		this.scopeId = scopeId;
	}
	public Integer getDayAgo() {
		return dayAgo;
	}
	public void setDayAgo(Integer dayAgo) {
		this.dayAgo = dayAgo;
	}
	public Integer getMonAgo() {
		return monAgo;
	}
	public void setMonAgo(Integer monAgo) {
		this.monAgo = monAgo;
	}
	public Timestamp getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Timestamp beginDate) {
		this.beginDate = beginDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserId() {
		return userId;
	}
	
	
	
}
