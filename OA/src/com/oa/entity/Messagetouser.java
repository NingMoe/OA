package com.oa.entity;

/**
 * 
 */

public class Messagetouser implements java.io.Serializable {

	// Fields

	private Integer id;
	private Userinfo userinfo;
	private Message message;
	private Integer ifRead;

	// Constructors

	/** default constructor */
	public Messagetouser() {
	}

	// /** full constructor */
	// public Messagetouser(Userinfo userinfo, Message message, Integer ifRead)
	// {
	// this.userinfo = userinfo;
	// this.message = message;
	// this.ifRead = ifRead;
	// }

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getIfRead() {
		return this.ifRead;
	}

	public void setIfRead(Integer ifRead) {
		this.ifRead = ifRead;
	}

}