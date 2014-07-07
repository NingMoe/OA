package com.oa.entity;

import java.sql.Timestamp;


/**
 * Accessoryfile entity. @author MyEclipse Persistence Tools
 */

public class Accessoryfile  implements java.io.Serializable {


    // Fields    

     private Integer accessoryId;
     private Fileinfo fileinfo;
     private Filetypeinfo filetypeinfo;
     private String accessoryName;
     private Integer accessorySize;
     private Timestamp createDate;
     private String accessoryPath;


    // Constructors

    /** default constructor */
    public Accessoryfile() {
    }

    
    /** full constructor */
    public Accessoryfile(Fileinfo fileinfo, Filetypeinfo filetypeinfo, String accessoryName, Integer accessorySize, Timestamp createDate, String accessoryPath) {
        this.fileinfo = fileinfo;
        this.filetypeinfo = filetypeinfo;
        this.accessoryName = accessoryName;
        this.accessorySize = accessorySize;
        this.createDate = createDate;
        this.accessoryPath = accessoryPath;
    }

   
    // Property accessors

    public Integer getAccessoryId() {
        return this.accessoryId;
    }
    
    public void setAccessoryId(Integer accessoryId) {
        this.accessoryId = accessoryId;
    }

    public Fileinfo getFileinfo() {
        return this.fileinfo;
    }
    
    public void setFileinfo(Fileinfo fileinfo) {
        this.fileinfo = fileinfo;
    }

    public Filetypeinfo getFiletypeinfo() {
        return this.filetypeinfo;
    }
    
    public void setFiletypeinfo(Filetypeinfo filetypeinfo) {
        this.filetypeinfo = filetypeinfo;
    }

    public String getAccessoryName() {
        return this.accessoryName;
    }
    
    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public Integer getAccessorySize() {
        return this.accessorySize;
    }
    
    public void setAccessorySize(Integer accessorySize) {
        this.accessorySize = accessorySize;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getAccessoryPath() {
        return this.accessoryPath;
    }
    
    public void setAccessoryPath(String accessoryPath) {
        this.accessoryPath = accessoryPath;
    }
   








}