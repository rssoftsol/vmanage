package com.imanage.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tsms_sender")
public class ESMSSender {
	
	private ClubDetails clubDetails;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private String id;
	
	@NotEmpty(message="Sender Id is mandatory")
	@Size(max=40,message="Maximum length is 40")
	private String senderId;
	
	@Column(name="SMSTEXT",length=20)
	@NotEmpty(message="SMS text is mandatory")
	@Size(max=160,message="Maximum length is 160")
	private String smsText;
	
	private String route;
	
	public ESMSSender(){
		super();
	}
	
	public ESMSSender(String senderId, String route, String smsText) {
		super();
		this.senderId = senderId;
		this.route = route;
		this.smsText = smsText;
	}

	@ManyToOne  
	@JoinColumn(name = "clubId")
	public ClubDetails getClubDetails() {
		return clubDetails;
	}
	
	public void setClubDetails(ClubDetails clubDetails) {
		this.clubDetails = clubDetails;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	public String getSmsText() {
		return smsText;
	}

	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}

	@Override
	public String toString() {
		return senderId;
	}
}
