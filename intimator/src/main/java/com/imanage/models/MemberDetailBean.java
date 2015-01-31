package com.imanage.models;

import java.sql.Date;

public class MemberDetailBean {
	private String phone;
	private String name;
	private Date expirydate;
	private Integer id;
	
	public MemberDetailBean(String phone, String name, Date expirydate, Integer id) {
		super();
		this.phone = phone;
		this.name = name;
		this.expirydate = expirydate;
		this.id = id;
	}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}
	
	
	
}
