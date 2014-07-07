package com.oa.util;

import java.io.Serializable;
import java.sql.Timestamp;

public class Column implements Serializable,Comparable<Column>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;
	private String title;
//	private Integer type;
	private Integer sort;
//	private Date date;
	private Timestamp date;
	private String metaTitle;
	private String meateKey;
	private String metaDescription;
	private Integer openConnent;
	private Integer view;
	private String url;
	private String publishUser;
	private String urlPage;
	private Integer maxResult;
	
	private Integer leaf;
	private String modeUrl;
	private String image;
	private Integer depth;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getMetaTitle() {
		return metaTitle;
	}
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	public String getMeateKey() {
		return meateKey;
	}
	public void setMeateKey(String meateKey) {
		this.meateKey = meateKey;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public Integer getOpenConnent() {
		return openConnent;
	}
	public void setOpenConnent(Integer openConnent) {
		this.openConnent = openConnent;
	}
	public Integer getView() {
		return view;
	}
	public void setView(Integer view) {
		this.view = view;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPublishUser() {
		return publishUser;
	}
	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getLeaf() {
		return leaf;
	}
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}
	public String getModeUrl() {
		return modeUrl;
	}
	public void setModeUrl(String modeUrl) {
		this.modeUrl = modeUrl;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
	private String subIdent;
	
	private double index;
	@Override
	public int compareTo(Column o) {
		if(o != null)
		{
			if(o.getIndex() >this.getIndex())
			{
				return -1;
			}
			else if(o.getIndex()==this.getIndex())
			{
				return -1;
			}
			else
			{
				return 1;
			}
			
		}
		return 0;
	}
	public String getSubIdent() {
		return subIdent;
	}
	public void setSubIdent(String subIdent) {
		this.subIdent = subIdent;
	}
	public double getIndex() {
		return index;
	}
	public void setIndex(double index) {
		this.index = index;
	}
	
	public String getUrlPage() {
		return urlPage;
	}
	public void setUrlPage(String urlPage) {
		this.urlPage = urlPage;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getMaxResult() {
		return maxResult;
	}
	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}


	public static final String SORT = "sort";
	
}
