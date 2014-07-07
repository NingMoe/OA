package com.oa.entity;

import java.util.HashSet;
import java.util.Set;


/**
 * Branchinfo entity. @author MyEclipse Persistence Tools
 */

public class Branchinfo  implements java.io.Serializable {


    // Fields    

     private Integer branchId;
     private String branchName;
     private String branchShortName;
     private Set departinfos = new HashSet(0);


    // Constructors

    /** default constructor */
    public Branchinfo() {
    }

	/** minimal constructor */
    public Branchinfo(String branchName, String branchShortName) {
        this.branchName = branchName;
        this.branchShortName = branchShortName;
    }
    
    /** minimal constructor */
    public Branchinfo(Integer branchId,String branchName, String branchShortName) {
    	this.branchId=branchId;
        this.branchName = branchName;
        this.branchShortName = branchShortName;
    }
    
    /** full constructor */
    public Branchinfo(String branchName, String branchShortName, Set departinfos) {
        this.branchName = branchName;
        this.branchShortName = branchShortName;
        this.departinfos = departinfos;
    }

   
    // Property accessors

    public Integer getBranchId() {
        return this.branchId;
    }
    
    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return this.branchName;
    }
    
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchShortName() {
        return this.branchShortName;
    }
    
    public void setBranchShortName(String branchShortName) {
        this.branchShortName = branchShortName;
    }

    public Set getDepartinfos() {
        return this.departinfos;
    }
    
    public void setDepartinfos(Set departinfos) {
        this.departinfos = departinfos;
    }
   

	

	private Integer depth;
	
	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Integer getDepth() {
		return depth;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getPid() {
		return pid;
	}




	private Integer pid;







}