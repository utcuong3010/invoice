package com.bubanking.forms;

import org.apache.commons.lang.StringUtils;

import com.bubanking.commons.Commons;


public class SearchInvoiceForm extends SearchForm{

	private int type;
	
	private String dateFrom;
	private String dateTo;
	private boolean isSearch;
	private int searchField;
	
	
	public SearchInvoiceForm() {
		super();
	}
	
	@Override
	public String createCondSearch(){
		StringBuffer buffer = new StringBuffer();		
		buffer.append(" invoice.type=" + type);// + " order by createDate desc ");
		buffer.append(" and invoice.status !=" + Commons.INVOICE_STATUS_DELETED);// + " order by createDate desc ");	
		switch (searchField) {
			case Commons.SEARCH_FIELD_ALL:
				buffer.append(" and (invoice.invoiceNo like '%" + getSearchKey() + "%'");
				buffer.append(" or invoice.productName like '%" + getSearchKey() + "%'");
				buffer.append(" or invoice.category.name like '%" + getSearchKey() + "%'");
				buffer.append(" or vendor.username like '%" + getSearchKey() + "%')");
				break;
			case Commons.SEARCH_FIELD_INVOICE_NO:
				buffer.append(" and invoice.invoiceNo like '%" + getSearchKey() + "%'");
				break;
			case Commons.SEARCH_FIELD_PRODUCT_NAME:
				buffer.append(" and invoice.productName like '%" + getSearchKey() + "%'");
				break;
				
			case Commons.SEARCH_FIELD_VENDOR_NAME:
				buffer.append(" and vendor.username like '%" + getSearchKey() + "%'");
				break;
			case Commons.SEARCH_FIELD_CATEGORY_NAME:
				buffer.append(" and invoice.category.name like '%" + StringUtils.upperCase(getSearchKey()) + "%'");
				break;
	
			default:
				break;
		}		
				
		buffer.append(" and invoice.createDate between '" + getDateFrom() + "' and '" + getDateTo() + "'");
		//order
		buffer.append(" order by invoice.createDate desc");
				
		return buffer.toString();
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	

	
	
	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

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
	 * @param isSearch
	 *            the isSearch to set
	 */
	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	

	

	public int getSearchField() {
		return searchField;
	}

	public void setSearchField(int searchField) {
		this.searchField = searchField;
	}

	@Override
	public String toString() {
		return "[type=" + type + ",start=" + getStart() + ",dateFrom=" + dateFrom
				+ ",dateTo=" + dateTo + ",searchKey=" + getSearchKey()
				+ ",isSearch=" + isSearch + ",searchField=" + searchField
				+ ",page=" + getPage() + ",limit=" + getLimit() + "]";
	}

}
