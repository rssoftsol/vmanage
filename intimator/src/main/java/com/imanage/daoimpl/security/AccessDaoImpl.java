package com.imanage.daoimpl.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imanage.dao.security.AccessDao;
import com.imanage.mapper.PasswordResetReqMapper;
import com.imanage.models.ClubDetails;
import com.imanage.models.security.PasswordResetReqBean;

@Repository("accessDao")
@Transactional(propagation = Propagation.SUPPORTS)
public class AccessDaoImpl implements AccessDao {

	Logger logger = Logger.getLogger(AccessDaoImpl.class.getName());

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String checkEmailId(String toEmailId) {
		String sql = "select * from club_details where email='"
				+ toEmailId.toLowerCase().trim() + "'";
		/*List<Customer> customers = jdbcTemplate
				.query(sql, new CustomerMapper());
		if (!customers.isEmpty()) {
			return customers.get(0).getLoginName();
		}*/
		return "";
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int insertResetPasswordEntry(
			final PasswordResetReqBean passwordResetReqBean) {
		int updatedRows = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				String sql = "INSERT INTO password_reset_requests "
						+ "(uid,username,time) VALUES (?, ?, (select now()))";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, passwordResetReqBean.getuId());
				ps.setString(2, passwordResetReqBean.getUserName());
				return ps;
			}
		});
		return updatedRows;
	}

	@Override
	public PasswordResetReqBean checkForValidLink(String uId) {
		String sql = "select * from password_reset_requests where uid='" + uId
				+ "'";
		List<PasswordResetReqBean> resetReqBeans = jdbcTemplate.query(sql,
				new PasswordResetReqMapper());
		if (!resetReqBeans.isEmpty()) {
			return resetReqBeans.get(0);
		}
		return new PasswordResetReqBean();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int resetPassword(final ClubDetails clubDetails, final String uId) {
		String sql = "update customer set password=?"
				+ " where loginName=(select loginName from password_reset_requests where uid=?)";

		int rowsUpdated = jdbcTemplate.update(sql,
				new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setString(1, clubDetails.getPassword());
						ps.setString(2, uId);
					}
				});
		if (rowsUpdated > 0) {
			sql = "delete from password_reset_requests where uid=?";

			jdbcTemplate.update(sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, uId);
				}
			});
		}
		return rowsUpdated;
	}

	@Override
	public boolean validateOldPassword(ClubDetails clubDetails) {
		String sql = "select password from club_details where username='"
				+ clubDetails.getUsername() + "'";
		List<String> passwords = jdbcTemplate.queryForList(sql, String.class);
		if (!passwords.isEmpty()) {
			String originalPassword = passwords.get(0);
			if (originalPassword.equalsIgnoreCase(clubDetails.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int updatePassword(final ClubDetails clubDetails) {
		String sql = "update customer set password=?"
				+ " where loginName=?";

		int rowsUpdated = jdbcTemplate.update(sql,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setString(1, clubDetails.getNewPassword());
						ps.setString(2, clubDetails.getUsername());
					}
				});
		return rowsUpdated;
	}
}
