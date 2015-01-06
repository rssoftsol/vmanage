package com.imanage.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.imanage.models.security.PasswordResetReqBean;

public class PasswordResetReqMapper implements RowMapper<PasswordResetReqBean> {

	Logger logger = Logger.getLogger(PasswordResetReqMapper.class.getName());

	public PasswordResetReqBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PasswordResetReqBean passwordResetReqBean= new PasswordResetReqBean();

		passwordResetReqBean.setuId(rs.getString("uId"));
		passwordResetReqBean.setUserName(rs.getString("loginName"));
		passwordResetReqBean.setTime(rs.getDate("time"));
		
		return passwordResetReqBean;
	}
}
