package com.mindtree.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public abstract class CustomHibernateDaoSupport extends HibernateDaoSupport {

	@Autowired
	public void setCustomHibernateTemplate(HibernateTemplate template) {
		template.setCheckWriteOperations(false);
		setHibernateTemplate(template);
	}
}
