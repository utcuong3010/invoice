package com.bubanking.infos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.bubanking.commons.Commons;
import com.bubanking.jsons.InvoiceInfoJson;
import com.bubanking.models.Invoice;

public class InvoiceInfo {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;		
	private String invoiceNo;		
	private Date invoiceDate;
	private String productName;	
	private Long money;
	private Integer type;	
	private Integer status;
	private String note;
	private Date createDate;
	private Date modifyDate;
	
	
	private UserInfo vendorInfo;
	
	private UserInfo centerVendorInfo;
	
	private InvoiceInfo processedInvoiceInfo;
	
	private CategoryInfo categoryInfo;
	
	private UserInfo authorInfo;

	/**
	 * @return the authorInfo
	 */
	public UserInfo getAuthorInfo() {
		return authorInfo;
		
	}


	/**
	 * @param authorInfo the authorInfo to set
	 */
	public void setAuthorInfo(UserInfo authorInfo) {
		this.authorInfo = authorInfo;
	}


	public InvoiceInfo() {
		
	}
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}


	public CategoryInfo getCategoryInfo() {
		return categoryInfo;
	}


	public void setCategoryInfo(CategoryInfo categoryInfo) {
		this.categoryInfo = categoryInfo;
	}


	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return  this.invoiceDate;
	}


	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}


	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}


	/**
	 * @return the money
	 */
	public Long getMoney() {
		return money;
	}


	/**
	 * @param money the money to set
	 */
	public void setMoney(Long money) {
		this.money = money;
	}


	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}


	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}


	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}


	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return this.createDate;
	}


	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return this.modifyDate;
	}


	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the vendorInfo
	 */
	public UserInfo getVendorInfo() {
		return vendorInfo;
	}


	/**
	 * @param vendorInfo the vendorInfo to set
	 */
	public void setVendorInfo(UserInfo vendorInfo) {
		this.vendorInfo = vendorInfo;
	}


	/**
	 * @return the processedInvoiceInfo
	 */
	public InvoiceInfo getProcessedInvoiceInfo() {
		return processedInvoiceInfo;
	}
	/**
	 * @return the centerVendorInfo
	 */
	public UserInfo getCenterVendorInfo() {
		return centerVendorInfo;
	}


	/**
	 * @param centerVendorInfo the centerVendorInfo to set
	 */
	public void setCenterVendorInfo(UserInfo centerVendorInfo) {
		this.centerVendorInfo = centerVendorInfo;
	}


	/**
	 * @param processedInvoiceInfo the processedInvoiceInfo to set
	 */
	public void setProcessedInvoiceInfo(InvoiceInfo processedInvoiceInfo) {
		this.processedInvoiceInfo = processedInvoiceInfo;
	}
	@Override
	public String toString() {
		return "[id=" + id + ",invoiceNo=" + invoiceNo + ",invoiceDate="+ invoiceDate +
		",productname="+ productName+ ",money="+ money + ",type="+ type+
		",status="+ status + ",note=" + note + ",createDate="+ createDate +
		",modifyDate=" + modifyDate + ",vendorInfo=" + vendorInfo + ",proccessedInvoiceInfo="+
		processedInvoiceInfo + "]";		
	}	
	public void copyProperties(Invoice invoice){
		if(invoice != null){
			this.id = invoice.getId();
			this.invoiceNo = invoice.getInvoiceNo();
			this.invoiceDate = invoice.getInvoiceDate();
			this.productName = invoice.getProductName();
			this.money = invoice.getMoney();
			this.status = invoice.getStatus();
			this.note = invoice.getNote();
			this.type = invoice.getType();
			this.createDate = invoice.getCreateDate();
			this.modifyDate = invoice.getModifyDate();
			if(invoice.getProcessedInvoice() != null) {
				this.processedInvoiceInfo = new InvoiceInfo();
				this.processedInvoiceInfo.copyProperties(invoice.getProcessedInvoice());			
			}
			//set vendor
			this.vendorInfo = new UserInfo();
			this.vendorInfo.copyProperties(invoice.getVendor());
			//set center vendor
			if(invoice.getCenterVendor() != null) {
				this.centerVendorInfo = new UserInfo();
				this.centerVendorInfo.copyProperties(invoice.getCenterVendor());
			}
			//set category
			if(invoice.getCategory() != null) {
				this.categoryInfo = new CategoryInfo();
				this.categoryInfo.copyProperties(invoice.getCategory());
			}
			//author info
			if(invoice.getAuthor() != null) {
				this.authorInfo = new UserInfo();
				this.authorInfo.copyProperties(invoice.getAuthor());
			}
		}	
	}	
	public void copyProperties(InvoiceInfoJson invoiceInfoJson) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat(Commons.DATE_FORMAT_DD_MM_YYYY);
		if(invoiceInfoJson != null){
			this.id = invoiceInfoJson.getId();
			this.invoiceNo = invoiceInfoJson.getInvoiceNo();
			this.invoiceDate = sdf.parse(invoiceInfoJson.getInvoiceDate());
			this.productName = invoiceInfoJson.getProductName();
			this.money = invoiceInfoJson.getMoneyValue();
			this.status = invoiceInfoJson.getStatus() == null ? Commons.INVOICE_STATUS_PENDING:invoiceInfoJson.getStatus();
			this.note = invoiceInfoJson.getNote();
			this.type = invoiceInfoJson.getType();
			//change full format
			sdf.applyPattern(Commons.DATE_FORMAT_DD_MM_YYYY_HH_MM_SS);
			if(StringUtils.isNotBlank(invoiceInfoJson.getCreateDate())) {
				this.createDate = sdf.parse(invoiceInfoJson.getCreateDate());
			}
			if(StringUtils.isNotBlank(invoiceInfoJson.getModifyDate())) {
				this.createDate = sdf.parse(invoiceInfoJson.getModifyDate());
			}
			if(invoiceInfoJson.getProcessedInvoiceId() != null) {
				InvoiceInfo processeInvoiceInfo = new InvoiceInfo();
				processeInvoiceInfo.setId(invoiceInfoJson.getProcessedInvoiceId());
				this.processedInvoiceInfo =  processeInvoiceInfo;
			}
			//set vendor
			if(invoiceInfoJson.getVendorId() != null) {
				this.vendorInfo = new UserInfo();
				this.vendorInfo.setId(invoiceInfoJson.getVendorId());
				this.vendorInfo.setUsername(invoiceInfoJson.getVendorName());
			}
			//set center vendor
			if(invoiceInfoJson.getCenterVendorId() != null) {			
				centerVendorInfo = new UserInfo();
				centerVendorInfo.setId(invoiceInfoJson.getCenterVendorId());
			}
			//set category name
			if(invoiceInfoJson.getCategoryId() != null) {
				categoryInfo = new CategoryInfo();
				categoryInfo.setId(invoiceInfoJson.getCategoryId());
			}
			//author
			if(invoiceInfoJson.getAuthorId() != null) {
				authorInfo = new UserInfo();
				authorInfo.setId(invoiceInfoJson.getAuthorId());
			}
		}
	}
		
	//get author
	
	
}
