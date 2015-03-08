package com.imanage.models;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="club_details")
public class ClubDetails{
	
	@Id
	@Column(name="club_id")
    @GeneratedValue
	private Integer club_id;
	
	@Column(name="username",length=20)
	@NotEmpty(message="User Name is mandatory")
	@Size(max=20,message="Maximum length is 20")
	private String username;
	
	@Column(name="password",length=40)
	@NotEmpty(message="Password is mandatory")
	private String password;
	
	@Column(name="newPassword",length=40)
	private String newPassword;
	
	@Column(name="membershiptype",length=1)
	private String membershiptype;
	
	@Column(name="phonenumber",length=20)
	@NotNull(message="Phone is mandatory")
	private Long phonenumber;
	
	@Column(name="clubname",length=20)
	@NotEmpty(message="Club name is mandatory")
	@Size(max=20,message="Maximum length is 20")
	private String clubname;
	
	@Column(name="email",length=35)
	@Email(message="Invalid email")
	@NotEmpty(message="Email is mandatory")
	@Size(max=40,message="Maximum length is 40")
	private String email;
	
	@Column(name="roleId")
	private Integer roleId;
	
	@Column(name="SMSTEXT",length=20)
	@NotEmpty(message="SMS text is mandatory")
	@Size(max=160,message="Maximum length is 160")
	private String smsText;
	
	@Column(name="ISACCOUNTATIVE",length=1)
	private String isAccountative;
	
	private Set<MemberDetails> memberDetails;
	
	private Timestamp createdDate;
	
	private Timestamp modifiedDate;
	
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

	public Long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(Long phonenumber) {
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

	@OneToMany(fetch=FetchType.EAGER,mappedBy = "clubDetails")
	public Set<MemberDetails> getMemberDetails() {
		return memberDetails;
	}

	public void setMemberDetails(Set<MemberDetails> memberDetails) {
		this.memberDetails = memberDetails;
	}
	
	public String getSmsText() {
		return smsText;
	}

	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}

	public String getIsAccountative() {
		return isAccountative;
	}

	public void setIsAccountative(String isAccountative) {
		this.isAccountative = isAccountative;
	}
	
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "clubname:"+clubname+" email"+email+" membershiptype:"+membershiptype
				+" newPassword:"+newPassword+" password:"+password+
				" phonenumber:"+phonenumber+" username:"+username;
	}
}
