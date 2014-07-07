package com.oa.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Sysfun entity. @author MyEclipse Persistence Tools
 */

public class Sysfun implements java.io.Serializable {

	// Fields

	private Integer nodeId;
	private Sysfun sysfun;
	private String displayName;
	private String nodeUrl;
	private Integer displayOrder;
	private String iconCls;
	private String attributes;
	private Set rolerights = new HashSet(0);
	private Set sysfuns = new HashSet(0);

	// Constructors

	/** default constructor */
	public Sysfun() {
	}

	/** minimal constructor */
	public Sysfun(Sysfun sysfun, String displayName, Integer displayOrder) {
		this.sysfun = sysfun;
		this.displayName = displayName;
		this.displayOrder = displayOrder;
	}

	/** full constructor */
	public Sysfun(Sysfun sysfun, String displayName, String nodeUrl,
			Integer displayOrder, Set rolerights, Set sysfuns) {
		this.sysfun = sysfun;
		this.displayName = displayName;
		this.nodeUrl = nodeUrl;
		this.displayOrder = displayOrder;
		this.rolerights = rolerights;
		this.sysfuns = sysfuns;
	}

	// Property accessors

	public Integer getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public Sysfun getSysfun() {
		return this.sysfun;
	}

	public void setSysfun(Sysfun sysfun) {
		this.sysfun = sysfun;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getNodeUrl() {
		return this.nodeUrl;
	}

	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}

	public Integer getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Set getRolerights() {
		return this.rolerights;
	}

	public void setRolerights(Set rolerights) {
		this.rolerights = rolerights;
	}

	public Set getSysfuns() {
		return this.sysfuns;
	}

	public void setSysfuns(Set sysfuns) {
		this.sysfuns = sysfuns;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String sysfunAttr() {
		return getAttributes();
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}


}