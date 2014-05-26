package com.bubanking.daos.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.bubanking.daos.CategoryDao;
import com.bubanking.models.Category;

public class CategoryDaoImpl extends GenericDaoImpl<Category, Integer> implements 
	CategoryDao{
	@Override
	protected Class<Category> getEntityClass() {
		return Category.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> findCategoriesByName(String name) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.or(
				Restrictions.like("name", "%"+ name.toUpperCase() +"%"),
				Restrictions.like("description", "%"+ name +"%")
				));
		
		return getHibernateTemplate().findByCriteria(criteria);
	}
	

}
