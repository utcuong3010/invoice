package com.bubanking.daos;

import java.util.List;

import com.bubanking.models.Category;

public interface CategoryDao extends GenericDao<Category, Integer> {
	
	public List<Category> findCategoriesByName(String name);

}
