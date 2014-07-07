package com.oa.entity;



/**
 * Worktime entity. @author MyEclipse Persistence Tools
 */

public class Worktime  implements java.io.Serializable {


    // Fields    

     private Integer workTimeId;
     private String onDutyTime;
     private String offDutyTime;


    // Constructors

    /** default constructor */
    public Worktime() {
    }

    
    /** full constructor */
    public Worktime(String onDutyTime, String offDutyTime) {
        this.onDutyTime = onDutyTime;
        this.offDutyTime = offDutyTime;
    }

   
    // Property accessors

    public Integer getWorkTimeId() {
        return this.workTimeId;
    }
    
    public void setWorkTimeId(Integer workTimeId) {
        this.workTimeId = workTimeId;
    }

    public String getOnDutyTime() {
        return this.onDutyTime;
    }
    
    public void setOnDutyTime(String onDutyTime) {
        this.onDutyTime = onDutyTime;
    }

    public String getOffDutyTime() {
        return this.offDutyTime;
    }
    
    public void setOffDutyTime(String offDutyTime) {
        this.offDutyTime = offDutyTime;
    }
   








}