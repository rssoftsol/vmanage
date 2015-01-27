package com.imanage.services.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imanage.models.ClubDetails;
import com.imanage.models.MemberDetails;

@Service("memberRegistrationService")
public class MemberRegistrationServiceImpl implements MemberRegistrationService{
	
	@Autowired
	MemberRegistrationDao memberRegistrationDao;
	
	public void setClubRegistrationDao(MemberRegistrationDao memberRegistrationDao){
		this.memberRegistrationDao = memberRegistrationDao;
	}
	
	@Override
	public void save(MemberDetails memberDetails) {
		memberRegistrationDao.save(memberDetails);
	}

	@Override
	public void update(MemberDetails memberDetails) {
		memberRegistrationDao.update(memberDetails);
		
	}

	@Override
	public void delete(MemberDetails memberDetails) {
		memberRegistrationDao.delete(memberDetails);
	}

	@Override
	public MemberDetails findByMemid(String memid) {
		return memberRegistrationDao.findByMemid(memid);
	}

}
