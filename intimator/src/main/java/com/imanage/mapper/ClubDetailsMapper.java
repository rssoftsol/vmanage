package com.imanage.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import org.springframework.jdbc.core.RowMapper;

import com.imanage.models.ClubDetails;
import com.imanage.models.MemberDetails;

public class ClubDetailsMapper implements RowMapper<ClubDetails>{
	  
	/***
	 * gets User object by binding values received from resultset
	 * @param <code>ResultSet</code>
	 * @return <code>User</code>
	 */
	public ClubDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		ClubDetails clubDetails = new ClubDetails();
		clubDetails.setUsername(rs.getString("username"));
		clubDetails.setPassword(rs.getString("password"));
		clubDetails.setNewPassword(rs.getString("newPassword"));
		clubDetails.setClubname(rs.getString("clubname"));
		clubDetails.setEmail(rs.getString("email"));
		//clubDetails.setMemberDetails(new HashSet<MemberDetails>());
		clubDetails.setMembershiptype(rs.getString("membershiptype"));
		clubDetails.setPhonenumber(rs.getString("phonenumber"));
		clubDetails.setClub_id(rs.getInt("club_id"));
		clubDetails.setRoleId(rs.getInt("roleId"));
		return clubDetails;
	}
}