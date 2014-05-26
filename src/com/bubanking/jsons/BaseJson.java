package com.bubanking.jsons;

import java.util.HashMap;
import java.util.Map;

public class BaseJson {
	
	private int totalCount;
	private boolean success;
	private Map<String, String> errors;
	
	public BaseJson() {
		success = false;
		errors = new HashMap<String, String>();
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the errors
	 */
	public Map<String, String> getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	
		

}
