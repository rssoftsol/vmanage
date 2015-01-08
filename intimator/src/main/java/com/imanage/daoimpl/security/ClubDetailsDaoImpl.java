package com.imanage.daoimpl.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.imanage.dao.security.ClubDetailsDao;
import com.imanage.mapper.ClubDetailsMapper;
import com.imanage.models.ClubDetails;
import com.imanage.models.MemberDetails;

@Repository("clubDetailsDao")
public class ClubDetailsDaoImpl implements ClubDetailsDao {

	Logger logger = Logger.getLogger(ClubDetailsDaoImpl.class.getName());

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public ClubDetails checkLoginDetails(String userName) {
		logger.info("jdbcTemplate : " + jdbcTemplate);
		String query = "select * from club_details where username = '"+userName+"'";
		/*ClubDetails clubDetails = new ClubDetails();
		clubDetails.setClub_id(1);
		clubDetails.setClubname("gym");
		clubDetails.setEmail("otari.rahul@gmail.com");
		clubDetails.setMemberDetails(new HashSet<MemberDetails>());
		clubDetails.setMembershiptype("permanent");
		clubDetails.setNewPassword("");
		clubDetails.setPassword("2689c9744c6f58db9d6d484803d9a035757d8fbc");
		clubDetails.setPhonenumber("12133");
		clubDetails.setUsername("rahul");
		clubDetails.setRoleId(1);*/
		List<ClubDetails> clubDetailsList = jdbcTemplate.query(query, new ClubDetailsMapper());
		//clubDetailsList.add(clubDetails);
		logger.info("user : " + clubDetailsList);
		if (clubDetailsList.isEmpty()) {
			logger.info("userList is empty");
			return new ClubDetails();
		} else {
			logger.info("got user object");
			return clubDetailsList.get(0);
		}
	}

}
