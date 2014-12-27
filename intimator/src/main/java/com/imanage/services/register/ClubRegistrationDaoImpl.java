package com.imanage.services.register;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.imanage.models.ClubDetails;
import com.imanage.util.CustomHibernateDaoSupport;

@Repository("clubRegistrationDao")
public class ClubRegistrationDaoImpl extends CustomHibernateDaoSupport implements ClubRegistrationDao{

	@Override
	public void save(ClubDetails clubDetails) {
		getHibernateTemplate().save(clubDetails);
	}

	@Override
	public void update(ClubDetails clubDetails) {
		getHibernateTemplate().update(clubDetails);
	}

	@Override
	public void delete(ClubDetails clubDetails) {
		getHibernateTemplate().delete(clubDetails);
	}

	@Override
	public ClubDetails findBySpecific(String value) {
		List list = getHibernateTemplate().find("from ClubDetails where username=?", value);
		if(list != null && list.size()>0){
			return (ClubDetails)list.get(0);
		}
		return null;
	}
	
}
