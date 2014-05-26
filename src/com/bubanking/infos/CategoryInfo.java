package com.bubanking.infos;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.bubanking.models.Category;

public class CategoryInfo extends Category {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryInfo() {
		super();
	}
	
	public void copyProperties(Category category){
		try {
			BeanUtils.copyProperties(this,category);
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
