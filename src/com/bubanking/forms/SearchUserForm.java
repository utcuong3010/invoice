package com.bubanking.forms;

import com.bubanking.commons.Commons;

public class SearchUserForm extends SearchForm {
	
	
	private String dateFrom;
	private String dateTo;
	private boolean isSearch;
	private int searchField;
	
	
	public SearchUserForm() {
		super();
	}

	@Override
	public String createCondSearch() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" type=" + Commons.USER_TYPE_OPERATOR);
		buffer.append(" and status != "+ Commons.USER_STATUS_DELETED);
		switch (searchField) {
		case Commons.SEARCH_FIELD_USER_FULL_NAME:
			buffer.append(" and fullname like '%" + getSearchKey() + "%'");
			break;
		case Commons.SEARCH_FIELD_USER_NAME:
			buffer.append(" and username like '%" + getSearchKey() + "%'");
			break;
		case Commons.SEARCH_FIELD_USER_EMAIL:
			buffer.append(" and email like '%" + getSearchKey() + "%'");
			break;
		case Commons.SEARCH_FIELD_USER_PHONE:
			buffer.append(" and phone like '%" + getSearchKey() + "%'");
			break;
		default:
			buffer.append(" and (fullname like '%" + getSearchKey() + "%'");
			buffer.append(" or username like '%" + getSearchKey() + "%'");
			buffer.append(" or email like '%" + getSearchKey() + "%'");
			buffer.append(" or phone like '%" + getSearchKey() + "%')");
			break;
		}
		
		
		buffer.append(" and createDate between '" + getDateFrom() + "' and '" + getDateTo() + "'");
		//order
		buffer.append(" order by createDate desc");
		return buffer.toString();
	}

	/**
	 * @return the dateFrom
	 */
	public String getDateFrom() {
		return dateFrom;
	}

	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the dateTo
	 */
	public String getDateTo() {
		return dateTo;
	}

	/**
	 * @param dateTo the dateTo to set
	 */
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * @return the isSearch
	 */
	public boolean isSearch() {
		return isSearch;
	}

	/**
	 * @param isSearch the isSearch to set
	 */
	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	/**
	 * @return the searchField
	 */
	public int getSearchField() {
		return searchField;
	}

	/**
	 * @param searchField the searchField to set
	 */
	public void setSearchField(int searchField) {
		this.searchField = searchField;
	}
	
	
	

}
