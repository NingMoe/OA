package com.oa.util;

import java.util.Map;

/**
 * 树对象
 * @author ZhuYichuan
 * 
 */
public class Tree {

	private int id;
	private String text;
	private String iconCls;
	
	private boolean checked;

	private String state;

	private int pid;
	private String ptext;
	private Map<String, Object> attributes;
	
	
	public Tree() {	}
	public Tree(Integer id) {
		this.id = id;
	}
	public Tree( String text, Integer pid) {
		this.text = text;
		this.pid = pid;
	}
	public Tree(Integer id, String text, Integer pid) {
		this.id = id;
		this.text = text;
		this.pid = pid;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPtext() {
		return ptext;
	}

	public void setPtext(String ptext) {
		this.ptext = ptext;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}


}
