package com.imanage.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="tsms_credit")
public class ESMSCreditBal {
	private ClubDetails clubDetails;
	
	@NotNull(message="Club Id cannot be empty")
	private Integer clubId;
	
	private String senderId;
	
	@NotNull(message="Balance cannot be empty")
	private Integer balance;
	
	private String route;
	
	public ESMSCreditBal(){
		super();
	}
	
	public ESMSCreditBal(String senderId, int balance, String route) {
		super();
		this.senderId = senderId;
		this.balance = balance;
		this.route = route;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public ClubDetails getClubDetails() {
		return clubDetails;
	}
	
	public void setClubDetails(ClubDetails clubDetails) {
		this.clubDetails = clubDetails;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	
	@GenericGenerator(name = "generator", strategy = "foreign", 
			parameters = @Parameter(name = "property", value = "clubDetails"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "CLUBID", unique = true, nullable = false)
	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}
	
	public static void main(String[] args) {
		Integer i = 0;
		System.out.println("i==>"+i);
	}
}
