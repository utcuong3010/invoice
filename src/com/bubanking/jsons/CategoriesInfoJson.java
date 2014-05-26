package com.bubanking.jsons;

import java.util.ArrayList;
import java.util.List;

import com.bubanking.infos.CategoryInfo;

public class CategoriesInfoJson extends BaseJson {

	public CategoriesInfoJson() {
		super();
	}
	private List<CategoryInfo> categories = new ArrayList<CategoryInfo>();
	/**
	 * @return the categories
	 */
	public List<CategoryInfo> getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<CategoryInfo> categories) {
		this.categories = categories;
	}	
	
}
