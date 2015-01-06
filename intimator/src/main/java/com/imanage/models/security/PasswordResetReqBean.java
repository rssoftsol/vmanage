package com.imanage.models.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="password_reset_requests",schema = "intimatordev")
public class PasswordResetReqBean {

	@Id
    @Column(name="uId",length=35)
    @GeneratedValue
	private String uId;
	
	@Column(name="username",length=20)
	private String userName;
	
	@Column(name="time")
	private Date time;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}