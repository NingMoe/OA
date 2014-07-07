package com.oa.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message  implements java.io.Serializable {


    // Fields    

     private Integer messageId;
     private Messagetype messagetype;
     private String title;
     private String content;
     private Timestamp beginTime;
     private Timestamp endTime;
     private String fromUserId;
     private Integer ifPublish;
     private Timestamp recordTime;
     private Set messagetousers = new HashSet(0);
     private Set readcommonmessages = new HashSet(0);


    // Constructors

    /** default constructor */
    public Message() {
    }

	/** minimal constructor */
    public Message(Messagetype messagetype, String title, String content, Timestamp beginTime, Timestamp endTime, String fromUserId, Integer ifPublish, Timestamp recordTime) {
        this.messagetype = messagetype;
        this.title = title;
        this.content = content;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.fromUserId = fromUserId;
        this.ifPublish = ifPublish;
        this.recordTime = recordTime;
    }
    
    /** full constructor */
    public Message(Messagetype messagetype, String title, String content, Timestamp beginTime, Timestamp endTime, String fromUserId, Integer ifPublish, Timestamp recordTime, Set messagetousers, Set readcommonmessages) {
        this.messagetype = messagetype;
        this.title = title;
        this.content = content;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.fromUserId = fromUserId;
        this.ifPublish = ifPublish;
        this.recordTime = recordTime;
        this.messagetousers = messagetousers;
        this.readcommonmessages = readcommonmessages;
    }

   
    // Property accessors

    public Integer getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Messagetype getMessagetype() {
        return this.messagetype;
    }
    
    public void setMessagetype(Messagetype messagetype) {
        this.messagetype = messagetype;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getBeginTime() {
        return this.beginTime;
    }
    
    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getFromUserId() {
        return this.fromUserId;
    }
    
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getIfPublish() {
        return this.ifPublish;
    }
    
    public void setIfPublish(Integer ifPublish) {
        this.ifPublish = ifPublish;
    }

    public Timestamp getRecordTime() {
        return this.recordTime;
    }
    
    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    public Set getMessagetousers() {
        return this.messagetousers;
    }
    
    public void setMessagetousers(Set messagetousers) {
        this.messagetousers = messagetousers;
    }

    public Set getReadcommonmessages() {
        return this.readcommonmessages;
    }
    
    public void setReadcommonmessages(Set readcommonmessages) {
        this.readcommonmessages = readcommonmessages;
    }
   








}