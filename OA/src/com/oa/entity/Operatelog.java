package com.oa.entity;

import java.sql.Timestamp;


/**
 * Operatelog entity. @author MyEclipse Persistence Tools
 */

public class Operatelog  implements java.io.Serializable {


    // Fields    

     private Integer operateId;
     private Userinfo userinfo;
     private String operateName;
     private String objectId;
     private String operateDesc;
     private Timestamp operateTime;


    // Constructors

    /** default constructor */
    public Operatelog() {
    }

    
    /** full constructor */
    public Operatelog(Userinfo userinfo, String operateName, String objectId, String operateDesc, Timestamp operateTime) {
        this.userinfo = userinfo;
        this.operateName = operateName;
        this.objectId = objectId;
        this.operateDesc = operateDesc;
        this.operateTime = operateTime;
    }

   
    // Property accessors

    public Integer getOperateId() {
        return this.operateId;
    }
    
    public void setOperateId(Integer operateId) {
        this.operateId = operateId;
    }

    public Userinfo getUserinfo() {
        return this.userinfo;
    }
    
    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public String getOperateName() {
        return this.operateName;
    }
    
    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getObjectId() {
        return this.objectId;
    }
    
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getOperateDesc() {
        return this.operateDesc;
    }
    
    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    public Timestamp getOperateTime() {
        return this.operateTime;
    }
    
    public void setOperateTime(Timestamp operateTime) {
        this.operateTime = operateTime;
    }
   








}