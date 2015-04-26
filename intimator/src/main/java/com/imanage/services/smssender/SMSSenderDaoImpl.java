package com.imanage.services.smssender;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.imanage.models.ESMSSender;
import com.imanage.util.CustomHibernateDaoSupport;

@Repository("smsSenderDao")
public class SMSSenderDaoImpl extends CustomHibernateDaoSupport implements SMSSenderDao{

	@Override
	public void save(ESMSSender esmsSender) {
		getHibernateTemplate().save(esmsSender);
	}

	@Override
	public void update(ESMSSender esmsSender) {
		getHibernateTemplate().update(esmsSender);
	}

	@Override
	public void delete(ESMSSender esmsSender) {
		getHibernateTemplate().delete(esmsSender);
	}

	@Override
	public List<ESMSSender> findSMSSender(String clubid) {
		List list = getHibernateTemplate().find("from tsms_details where clubid=?", clubid);
		return list;
	}
	
}
