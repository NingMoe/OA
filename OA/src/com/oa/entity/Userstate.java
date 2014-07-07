package com.oa.entity;

import java.util.HashSet;
import java.util.Set;


/**
 * Userstate entity. @author MyEclipse Persistence Tools
 */

public class Userstate  implements java.io.Serializable {


    // Fields    

     private Integer userStateId;
     private String userStateName;
     private Set userinfos = new HashSet(0);


    // Constructors

    /** default constructor */
    public Userstate() {
    }

	/** minimal constructor */
    public Userstate(String userStateName) {
        this.userStateName = userStateName;
    }
    
    /** full constructor */
    public Userstate(String userStateName, Set userinfos) {
        this.userStateName = userStateName;
        this.userinfos = userinfos;
    }

   
    // Property accessors

    public Integer getUserStateId() {
        return this.userStateId;
    }
    
    public void setUserStateId(Integer userStateId) {
        this.userStateId = userStateId;
    }

    public String getUserStateName() {
        return this.userStateName;
    }
    
    public void setUserStateName(String userStateName) {
        this.userStateName = userStateName;
    }

    public Set getUserinfos() {
        return this.userinfos;
    }
    
    public void setUserinfos(Set userinfos) {
        this.userinfos = userinfos;
    }
   








}