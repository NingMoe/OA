package com.oa.action.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 968449107778592846L;

	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		
		try {
			filename = new String(filename.getBytes(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.filename = filename;
	}

	public InputStream getInputStream() throws FileNotFoundException,
			UnsupportedEncodingException {
		InputStream is = null;
		String realPath = ServletActionContext.getServletContext().getRealPath(
				File.separator + filename);
		is = new FileInputStream(realPath);

		filename = filename.substring(filename.lastIndexOf(File.separator) + 1);
		System.out.println("filename = " + filename);
		
		return is;
	}

	public String downloadFile() {
		return SUCCESS;
	}
}
