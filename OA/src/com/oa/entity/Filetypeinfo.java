package com.oa.entity;

import java.util.HashSet;
import java.util.Set;


/**
 * Filetypeinfo entity. @author MyEclipse Persistence Tools
 */

public class Filetypeinfo  implements java.io.Serializable {


    // Fields    

     private Integer fileTypeId;
     private String fileTypeName;
     private String fileTypeImage;
     private String fileTypeSuffix;
     private Set fileinfos = new HashSet(0);
     private Set accessoryfiles = new HashSet(0);


    // Constructors

    /** default constructor */
    public Filetypeinfo() {
    }
    
    /** minimal constructor */
    public Filetypeinfo(String suffix) {
        this.fileTypeSuffix = suffix;
    }
    
    /** minimal constructor */
    public Filetypeinfo(String fileTypeName,String suffix) {
        this.fileTypeName = fileTypeName;
        this.fileTypeSuffix = suffix;
    }
    
    /** minimal constructor */
    public Filetypeinfo(Integer id,String fileTypeName,String suffix , String fileTypeImage) {
    	this.fileTypeId = id;
    	this.fileTypeName = fileTypeName;
        this.fileTypeImage = fileTypeImage;
        this.fileTypeSuffix = suffix;
    }
    
	/** minimal constructor */
    public Filetypeinfo(String fileTypeName,String suffix , String fileTypeImage) {
        this.fileTypeName = fileTypeName;
        this.fileTypeImage = fileTypeImage;
        this.fileTypeSuffix = suffix;
    }
    
    /** full constructor */
    public Filetypeinfo(String fileTypeName, String fileTypeImage, String fileTypeSuffix, Set fileinfos, Set accessoryfiles) {
        this.fileTypeName = fileTypeName;
        this.fileTypeImage = fileTypeImage;
        this.fileTypeSuffix = fileTypeSuffix;
        this.fileinfos = fileinfos;
        this.accessoryfiles = accessoryfiles;
    }

   
    // Property accessors

    public Integer getFileTypeId() {
        return this.fileTypeId;
    }
    
    public void setFileTypeId(Integer fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public String getFileTypeName() {
        return this.fileTypeName;
    }
    
    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getFileTypeImage() {
        return this.fileTypeImage;
    }
    
    public void setFileTypeImage(String fileTypeImage) {
        this.fileTypeImage = fileTypeImage;
    }

    public String getFileTypeSuffix() {
        return this.fileTypeSuffix;
    }
    
    public void setFileTypeSuffix(String fileTypeSuffix) {
        this.fileTypeSuffix = fileTypeSuffix;
    }

    public Set getFileinfos() {
        return this.fileinfos;
    }
    
    public void setFileinfos(Set fileinfos) {
        this.fileinfos = fileinfos;
    }

    public Set getAccessoryfiles() {
        return this.accessoryfiles;
    }
    
    public void setAccessoryfiles(Set accessoryfiles) {
        this.accessoryfiles = accessoryfiles;
    }
   








}