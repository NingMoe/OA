package com.oa.entity;

import java.sql.Timestamp;


/**
 * Manualsign entity. @author MyEclipse Persistence Tools
 */

public class Manualsign  implements java.io.Serializable {


    // Fields    

     private Integer signId;
     private Userinfo userinfo;
     private Timestamp signTime;
     private String signDesc;
     private Integer signTag;


    // Constructors

    /** default constructor */
    public Manualsign() {
    }

    
    /** full constructor */
    public Manualsign(Userinfo userinfo, Timestamp signTime, String signDesc, Integer signTag) {
        this.userinfo = userinfo;
        this.signTime = signTime;
        this.signDesc = signDesc;
        this.signTag = signTag;
    }

   
    // Property accessors

    public Integer getSignId() {
        return this.signId;
    }
    
    public void setSignId(Integer signId) {
        this.signId = signId;
    }

    public Userinfo getUserinfo() {
        return this.userinfo;
    }
    
    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public Timestamp getSignTime() {
        return this.signTime;
    }
    
    public void setSignTime(Timestamp signTime) {
        this.signTime = signTime;
    }

    public String getSignDesc() {
        return this.signDesc;
    }
    
    public void setSignDesc(String signDesc) {
        this.signDesc = signDesc;
    }

    public Integer getSignTag() {
        return this.signTag;
    }
    
    public void setSignTag(Integer signTag) {
        this.signTag = signTag;
    }
   








}