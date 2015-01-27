package com.imanage.services.members;

import com.imanage.models.MemberDetails;


public interface MemberRegistrationDao {
	void save(MemberDetails memberDetails);
	
	void update(MemberDetails memberDetails);
	
	void delete(MemberDetails memberDetails);
	
	MemberDetails findByMemid(String memid);

}
