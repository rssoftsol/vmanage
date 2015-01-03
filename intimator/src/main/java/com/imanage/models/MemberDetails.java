package com.imanage.models;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="member_details")
public class MemberDetails {
	private Integer id;
	private String memid;
	private String phone;
	private String name;
	private Date expirydate;
	
	
	private ClubDetails clubDetails;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
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
	
	public void setExpirydate(String expirydate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(new java.util.Date().getTime());
		try {
			date = new Date(dateFormat.parse(expirydate).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.expirydate = date;
	}
	
	@ManyToOne  
	@JoinColumn(name = "USERNAME")
	public ClubDetails getClubDetails() {
		return clubDetails;
	}
	
	public void setClubDetails(ClubDetails clubDetails) {
		this.clubDetails = clubDetails;
	}
	
}
