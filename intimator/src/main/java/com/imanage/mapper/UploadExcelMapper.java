package com.imanage.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.imanage.models.MemberDetails;

public class UploadExcelMapper implements RowMapper<MemberDetails>{
	  
	/***
	 * gets User object by binding values received from resultset
	 * @param <code>ResultSet</code>
	 * @return <code>User</code>
	 */
	public MemberDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MemberDetails memberDetails = new MemberDetails();
		String uploadtext = rs.getString("uploadtext");
		String[] memberDetailsArray = uploadtext.split("~");
		memberDetails.setMemid(memberDetailsArray[0]);
		memberDetails.setPhone(!"".equalsIgnoreCase(memberDetailsArray[0])?Long.valueOf(memberDetailsArray[1]):null);
		memberDetails.setName(memberDetailsArray[2]);
		try {
			memberDetails.setExpirydate(new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(memberDetailsArray[3]).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			memberDetails.setExpirydate(null);
		}
		return memberDetails;
	}
}