package com.project.model;

import java.io.Serializable;

public class Project implements Serializable {
			
	private String fileName;
	private int id;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		
		return "[fileName=" + fileName + ", id="+ id +"]\n";
	}
	
	
	
	
	
}
