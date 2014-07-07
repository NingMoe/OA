package com.oa.entity;



/**
 * Precontract entity. @author MyEclipse Persistence Tools
 */

public class Precontract  implements java.io.Serializable {


    // Fields    

     private Integer preContractId;
     private Schedule schedule;
     private Userinfo userinfo;


    // Constructors

    /** default constructor */
    public Precontract() {
    }

    
    /** full constructor */
    public Precontract(Schedule schedule, Userinfo userinfo) {
        this.schedule = schedule;
        this.userinfo = userinfo;
    }

   
    // Property accessors

    public Integer getPreContractId() {
        return this.preContractId;
    }
    
    public void setPreContractId(Integer preContractId) {
        this.preContractId = preContractId;
    }

    public Schedule getSchedule() {
        return this.schedule;
    }
    
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Userinfo getUserinfo() {
        return this.userinfo;
    }
    
    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }
   








}