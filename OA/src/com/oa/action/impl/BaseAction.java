package com.oa.action.impl;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware,
		RequestAware {

	private static final long serialVersionUID = -1300730974990422001L;

	private Map<String, Object> session;
	private Map<String, Object> request;

	// //////////////////////// getter&setter /////////////////////////////
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}

	public Map<String, Object> getRequest() {
		return this.request;
	}


}
