package com.oa.entity;

import java.sql.Timestamp;


/**
 * Mynote entity. @author MyEclipse Persistence Tools
 */

public class Mynote  implements java.io.Serializable {


    // Fields    

     private Integer noteId;
     private Userinfo userinfo;
     private String noteTitle;
     private String noteContent;
     private Timestamp createTime;


    // Constructors

    /** default constructor */
    public Mynote() {
    }

	/** minimal constructor */
    public Mynote(Userinfo userinfo, String noteTitle, Timestamp createTime) {
        this.userinfo = userinfo;
        this.noteTitle = noteTitle;
        this.createTime = createTime;
    }
    
    /** full constructor */
    public Mynote(Userinfo userinfo, String noteTitle, String noteContent, Timestamp createTime) {
        this.userinfo = userinfo;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.createTime = createTime;
    }

   
    // Property accessors

    public Integer getNoteId() {
        return this.noteId;
    }
    
    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Userinfo getUserinfo() {
        return this.userinfo;
    }
    
    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public String getNoteTitle() {
        return this.noteTitle;
    }
    
    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return this.noteContent;
    }
    
    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
   








}