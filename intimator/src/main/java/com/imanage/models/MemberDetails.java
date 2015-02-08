package com.imanage.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name="member_details")
public class MemberDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	@NotEmpty(message="Member Id is mandatory")
	private String memid;
	@NotNull(message="Phone is mandatory")
	@NumberFormat(style= Style.NUMBER)
	private Long phone;
	@NotEmpty(message="Name is mandatory")
	
	private String name;
	@NotNull(message="Expiry Date is mandatory")
	@Future(message="Invalid date")
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

	public Long getPhone() {
		return phone;
	}
	
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//
	public Date getExpirydate() {
		return expirydate;
	}
	
	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}
	
	@ManyToOne  
	@JoinColumn(name = "USERNAME")
	public ClubDetails getClubDetails() {
		return clubDetails;
	}
	
	public void setClubDetails(ClubDetails clubDetails) {
		this.clubDetails = clubDetails;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "phone:"+phone;
	}
	
}
