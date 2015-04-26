package com.imanage.services.smscredit;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.imanage.models.ESMSCreditBal;
import com.imanage.models.ESMSSender;
import com.imanage.services.smssender.SMSSenderDao;
import com.imanage.util.CustomHibernateDaoSupport;

@Repository("smsCreditBalDao")
public class SMSCreditdBalDaoImpl extends CustomHibernateDaoSupport implements SMSCreditdBalDao{

	@Override
	public void save(ESMSCreditBal smsCreditBal) {
		getHibernateTemplate().save(smsCreditBal);
	}

	@Override
	public void update(ESMSCreditBal smsCreditBal) {
		getHibernateTemplate().update(smsCreditBal);
	}

	@Override
	public void delete(ESMSCreditBal smsCreditBal) {
		getHibernateTemplate().delete(smsCreditBal);
	}

	@Override
	public ESMSCreditBal getSMSCreditBalance(String clubid) {
		ESMSCreditBal creditBal = null;
		List<ESMSCreditBal> list = getHibernateTemplate().find("from tsms_credit where clubid=?", clubid);
		if(list!=null && list.size()>0){
			creditBal = list.get(0);
		}
		return creditBal;
	}
	
}
