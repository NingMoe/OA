package com.oa.entity;



/**
 * Readcommonmessage entity. @author MyEclipse Persistence Tools
 */

public class Readcommonmessage  implements java.io.Serializable {


    // Fields    

     private Integer readId;
     private Userinfo userinfo;
     private Message message;


    // Constructors

    /** default constructor */
    public Readcommonmessage() {
    }

    
    /** full constructor */
    public Readcommonmessage(Userinfo userinfo, Message message) {
        this.userinfo = userinfo;
        this.message = message;
    }

   
    // Property accessors

    public Integer getReadId() {
        return this.readId;
    }
    
    public void setReadId(Integer readId) {
        this.readId = readId;
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
   








}