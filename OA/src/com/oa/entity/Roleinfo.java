package com.oa.entity;

import java.util.HashSet;
import java.util.Set;


/**
 * Roleinfo entity. @author MyEclipse Persistence Tools
 */

public class Roleinfo  implements java.io.Serializable {


    // Fields    

     private Integer roleId;
     private String roleName;
     private String roleDesc;
     private Set rolerights = new HashSet(0);
     private Set userinfos = new HashSet(0);


    // Constructors

    /** default constructor */
    public Roleinfo() {
    }

    /** minimal constructor */
    public Roleinfo(Integer roleId) {
        this.roleId = roleId;
    }
	/** minimal constructor */
    public Roleinfo(String roleName) {
        this.roleName = roleName;
    }
    /** minimal constructor */
    public Roleinfo(String roleName,String roleDesc) {
        this.roleName = roleName;
        this.roleDesc=roleDesc;
    }
    /** minimal constructor */
    public Roleinfo(Integer roleId,String roleName,String roleDesc) {
    	this.roleId=roleId;
        this.roleName = roleName;
        this.roleDesc=roleDesc;
    }
    
    /** full constructor */
    public Roleinfo(String roleName, String roleDesc, Set rolerights, Set userinfos) {
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.rolerights = rolerights;
        this.userinfos = userinfos;
    }

   
    // Property accessors

    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return this.roleDesc;
    }
    
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Set getRolerights() {
        return this.rolerights;
    }
    
    public void setRolerights(Set rolerights) {
        this.rolerights = rolerights;
    }

    public Set getUserinfos() {
        return this.userinfos;
    }
    
    public void setUserinfos(Set userinfos) {
        this.userinfos = userinfos;
    }
   








}