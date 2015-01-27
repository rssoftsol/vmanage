package com.imanage.services.members;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.imanage.models.MemberDetails;
import com.imanage.util.CustomHibernateDaoSupport;

@Repository("memberRegistrationDao")
public class MemberRegistrationDaoImpl extends CustomHibernateDaoSupport implements MemberRegistrationDao{

	@Override
	public void save(MemberDetails memberDetails) {
		getHibernateTemplate().save(memberDetails);
	}

	@Override
	public void update(MemberDetails memberDetails) {
		getHibernateTemplate().update(memberDetails);
	}

	@Override
	public void delete(MemberDetails memberDetails) {
		getHibernateTemplate().delete(memberDetails);
	}

	@Override
	public MemberDetails findByMemid(String memid) {
		List list = getHibernateTemplate().find("from MemberDetails where memid=?", memid);
		if(list != null && list.size()>0){
			return (MemberDetails)list.get(0);
		}
		return null;
	}
	
}
