package com.bubanking.daos.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bubanking.daos.GenericDao;

public abstract class GenericDaoImpl<E, PK extends Serializable> extends HibernateDaoSupport
	implements GenericDao<E, PK>{
	
	protected abstract Class<E> getEntityClass();
	
	protected DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(getEntityClass());
  }

	@SuppressWarnings("unchecked")
	public PK save(E newInstance) {
		 return (PK) getHibernateTemplate().save(newInstance);
	}
	public void update(E transientObject) {
		getHibernateTemplate().update(transientObject);
	}
	public void saveOrUpdate(E transientObject) {
		getHibernateTemplate().saveOrUpdate(transientObject);
		
	}
	public void delete(E persistentObject) {		
		getHibernateTemplate().delete(persistentObject);
	}
	public E findById(PK id) {		
		return (E)getHibernateTemplate().get(getEntityClass(), id);
	}
	@SuppressWarnings("unchecked")
	public List<E> findAll() {	
		return getHibernateTemplate().findByCriteria(createDetachedCriteria());
	}

	@SuppressWarnings("unchecked")
	public List<E> findAllByProperty(String propertyName, Object value) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq(propertyName, value));
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
}
