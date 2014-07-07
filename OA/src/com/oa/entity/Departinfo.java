package com.oa.entity;

import java.util.List;



/**
 * Departinfo entity. @author MyEclipse Persistence Tools
 */

public class Departinfo  implements java.io.Serializable {


    // Fields    
     private Integer departId;
     private Userinfo userinfo;
     private Branchinfo branchinfo;
     private String departName;
     private String connectTelNo;
     private Long connectMobileTelNo;
     private String faxes;
     private List<Userinfo> employees;


    // Constructors

    /** default constructor */
    public Departinfo() {
    }

	/** minimal constructor */
    public Departinfo(Integer id) {
        this.departId = id;
    }
    
    /** minimal constructor */
    public Departinfo(Userinfo userinfo, Branchinfo branchinfo, String departName) {
        this.userinfo = userinfo;
        this.branchinfo = branchinfo;
        this.departName = departName;
    }
    
    /** full constructor */
    public Departinfo(Integer departId,Userinfo userinfo, Branchinfo branchinfo, String departName, String connectTelNo, Long connectMobileTelNo, String faxes) {
        this.departId = departId;
    	this.userinfo = userinfo;
        this.branchinfo = branchinfo;
        this.departName = departName;
        this.connectTelNo = connectTelNo;
        this.connectMobileTelNo = connectMobileTelNo;
        this.faxes = faxes;
    }

   
    // Property accessors

    public Integer getDepartId() {
        return this.departId;
    }
    
    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public Userinfo getUserinfo() {
        return this.userinfo;
    }
    
    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public Branchinfo getBranchinfo() {
        return this.branchinfo;
    }
    
    public void setBranchinfo(Branchinfo branchinfo) {
        this.branchinfo = branchinfo;
    }

    public String getDepartName() {
        return this.departName;
    }
    
    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Long getConnectMobileTelNo() {
        return this.connectMobileTelNo;
    }
    
    public void setConnectMobileTelNo(Long connectMobileTelNo) {
        this.connectMobileTelNo = connectMobileTelNo;
    }

	public void setEmployees(List<Userinfo> employees) {
		this.employees = employees;
	}

	public List<Userinfo> getEmployees() {
		return employees;
	}

	public String getConnectTelNo() {
		return connectTelNo;
	}

	public void setConnectTelNo(String connectTelNo) {
		this.connectTelNo = connectTelNo;
	}

	public String getFaxes() {
		return faxes;
	}

	public void setFaxes(String faxes) {
		this.faxes = faxes;
	}
   
	

}