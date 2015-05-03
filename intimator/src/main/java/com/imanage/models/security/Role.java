package com.imanage.models.security;

import java.io.File;
import java.util.Date;

import com.imanage.util.DateUtility;

public class Role {

	private String roleId;
	private String roleDescription;
	private String createdBy;
	private Date createdDate;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public void testmethod(String s, Boolean b){
		s = s + s;
		b = true;
	}
	private static String getPath(){
		String path = "";
		File f = new File("D://intimator//"+DateUtility.getTodaysDate("dd_MM_yyyy"));
		if(!f.exists()){
			System.out.println(f.mkdirs());
		}
		return path;
	}
	public static void main(String[] args) {
		getPath();
	}

}