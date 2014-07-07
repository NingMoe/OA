package com.oa.entity;

import java.sql.Timestamp;


/**
 * Loginlog entity. @author MyEclipse Persistence Tools
 */

public class Loginlog  implements java.io.Serializable {


    // Fields    

     private Integer loginId;
     private Userinfo userinfo;
     private Timestamp loginTime;
     private Integer ifSuccess;
     private String loginUserIp;
     private String loginDesc;


    // Constructors

    /** default constructor */
    public Loginlog() {
    }

	/** minimal constructor */
    public Loginlog(Userinfo userinfo, Timestamp loginTime, Integer ifSuccess, String loginUserIp) {
        this.userinfo = userinfo;
        this.loginTime = loginTime;
        this.ifSuccess = ifSuccess;
        this.loginUserIp = loginUserIp;
    }
    
    public Loginlog(Integer loginId) {

    	this.loginId=loginId;
    }
    /** full constructor */
    public Loginlog(Userinfo userinfo, Timestamp loginTime, Integer ifSuccess, String loginUserIp, String loginDesc) {
        this.userinfo = userinfo;
        this.loginTime = loginTime;
        this.ifSuccess = ifSuccess;
        this.loginUserIp = loginUserIp;
        this.loginDesc = loginDesc;
    }

   
    // Property accessors

    public Integer getLoginId() {
        return this.loginId;
    }
    
    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public Userinfo getUserinfo() {
        return this.userinfo;
    }
    
    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public Timestamp getLoginTime() {
        return this.loginTime;
    }
    
    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getIfSuccess() {
        return this.ifSuccess;
    }
    
    public void setIfSuccess(Integer ifSuccess) {
        this.ifSuccess = ifSuccess;
    }

    public String getLoginUserIp() {
        return this.loginUserIp;
    }
    
    public void setLoginUserIp(String loginUserIp) {
        this.loginUserIp = loginUserIp;
    }

    public String getLoginDesc() {
        return this.loginDesc;
    }
    
    public void setLoginDesc(String loginDesc) {
        this.loginDesc = loginDesc;
    }
   








}