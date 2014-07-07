package com.oa.entity;



/**
 * Roleright entity. @author MyEclipse Persistence Tools
 */

public class Roleright  implements java.io.Serializable {


    // Fields    

     private Integer roleRightId;
     private Sysfun sysfun;
     private Roleinfo roleinfo;


    // Constructors

    /** default constructor */
    public Roleright() {
    }

    
    /** full constructor */
    public Roleright(Sysfun sysfun, Roleinfo roleinfo) {
        this.sysfun = sysfun;
        this.roleinfo = roleinfo;
    }

   
    // Property accessors

    public Integer getRoleRightId() {
        return this.roleRightId;
    }
    
    public void setRoleRightId(Integer roleRightId) {
        this.roleRightId = roleRightId;
    }

    public Sysfun getSysfun() {
        return this.sysfun;
    }
    
    public void setSysfun(Sysfun sysfun) {
        this.sysfun = sysfun;
    }

    public Roleinfo getRoleinfo() {
        return this.roleinfo;
    }
    
    public void setRoleinfo(Roleinfo roleinfo) {
        this.roleinfo = roleinfo;
    }
   








}