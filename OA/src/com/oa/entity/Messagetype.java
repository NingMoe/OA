package com.oa.entity;

import java.util.HashSet;
import java.util.Set;


/**
 * Messagetype entity. 
 */

public class Messagetype  implements java.io.Serializable {


    // Fields    

     private Integer messageTypeId;
     private String messageTypeName;
     private String messageDesc;
     private Set messages = new HashSet(0);


    // Constructors

    /** default constructor */
    public Messagetype() {
    }

	/** minimal constructor */
    public Messagetype(String messageTypeName) {
        this.messageTypeName = messageTypeName;
    }
    
    /** full constructor */
    public Messagetype(String messageTypeName, String messageDesc, Set messages) {
        this.messageTypeName = messageTypeName;
        this.messageDesc = messageDesc;
        this.messages = messages;
    }

   
    // Property accessors

    public Integer getMessageTypeId() {
        return this.messageTypeId;
    }
    
    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public String getMessageTypeName() {
        return this.messageTypeName;
    }
    
    public void setMessageTypeName(String messageTypeName) {
        this.messageTypeName = messageTypeName;
    }

    public String getMessageDesc() {
        return this.messageDesc;
    }
    
    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }

    public Set getMessages() {
        return this.messages;
    }
    
    public void setMessages(Set messages) {
        this.messages = messages;
    }
   








}