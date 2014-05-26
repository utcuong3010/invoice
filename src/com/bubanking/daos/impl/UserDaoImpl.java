package com.bubanking.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.dao.support.DataAccessUtils;

import com.bubanking.daos.UserDao;
import com.bubanking.models.User;

public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {
	
	private Logger log = Logger.getLogger(UserDaoImpl.class);
	
	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
	public boolean deteleAll(List<User> users) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<User> find(String whereCond) {
		String hql = "from User ";
		if(StringUtils.isNotBlank(whereCond)) {
			hql += " where " + whereCond;
		}	
		return getHibernateTemplate().find(hql);
	}
	public int countUsers(String whereCond) {
		String hql = "select count(*) from User ";
		if(StringUtils.isNotBlank(whereCond)) {
			hql += " where " + whereCond;
		}		
		return DataAccessUtils.intResult(getHibernateTemplate().find(hql));
	}
	public List<User> find(String whereCond, int firstResult, int maxResult) {
		List<User> result = new ArrayList<User>();
		try {
			String hql = "from User ";
			if(StringUtils.isNotBlank(whereCond)) {
				hql = hql+ " where " + whereCond;
			}
			//create Query
			Query query = getSession().createQuery(hql);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			result = (List<User>)query.list();
		} catch (Exception e) {
			log.error("Find invoice with errors:" + e);
		}
		return result;
	}

}
