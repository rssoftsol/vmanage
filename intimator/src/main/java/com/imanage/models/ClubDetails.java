package com.imanage.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="club_details")
public class ClubDetails{
	
	@Id
	@Column(name="club_id")
    @GeneratedValue
	private Integer club_id;
	@Column(name="username",length=20)
	private String username;
	@Column(name="password",length=10)
	private String password;
	@Column(name="newPassword",length=10)
	private String newPassword;
	@Column(name="membershiptype",length=1)
	private String membershiptype;
	@Column(name="phonenumber",length=20)
	private String phonenumber;
	@Column(name="clubname",length=20)
	private String clubname;
	@Column(name="email",length=35)
	private String email;
	@Column(name="roleId")
	private Integer roleId;
	
	private Set<MemberDetails> memberDetails;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLUB_ID", unique = true, nullable = false)
	public Integer getClub_id() {
		return this.club_id;
	}

	public void setClub_id(Integer club_id) {
		this.club_id = club_id;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getMembershiptype() {
		return membershiptype;
	}
	
	public void setMembershiptype(String membershiptype) {
		this.membershiptype = membershiptype;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getClubname() {
		return clubname;
	}

	public void setClubname(String clubname) {
		this.clubname = clubname;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(mappedBy = "clubDetails")
	public Set<MemberDetails> getMemberDetails() {
		return memberDetails;
	}

	public void setMemberDetails(Set<MemberDetails> memberDetails) {
		this.memberDetails = memberDetails;
	}

}
