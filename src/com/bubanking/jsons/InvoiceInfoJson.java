package com.bubanking.jsons;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.servlet.support.RequestContext;

import com.bubanking.commons.Commons;
import com.bubanking.infos.CategoryInfo;
import com.bubanking.infos.InvoiceInfo;
import com.bubanking.infos.UserInfo;
import com.bubanking.services.InvoiceService;
import com.bubanking.services.UserService;


public class InvoiceInfoJson {
	
	private Long id;		
	private String invoiceNo;		
	private String invoiceDate;
	private String productName;	
	private String money;
	private Integer type;	
	private Integer status;
	private String statusString;
	private String note;
	private String createDate;
	private String modifyDate;
	
	//info vendor
	private Long vendorId;
	private String vendorName;
	
	//info center vendor
	private Long centerVendorId;
	private String centerVendorName;
	
	private Long processedInvoiceId;
	private String processedInvoiceNo;
	
	private Integer categoryId;
	private String categoryName;
	
	private Long authorId;
	private String authorName;

	
	
	/**
	 * @return the authorId
	 */
	public Long getAuthorId() {
		return authorId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public InvoiceInfoJson() {
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	

	public String getMoney() {
		return money;
	}
	public Long getMoneyValue() {
		NumberFormat nf = NumberFormat.getInstance();
		Long result = 0l;
		try {
			if(StringUtils.isNotEmpty(money)) {
				result = (Long)nf.parse(StringUtils.remove(money, "."));					
			}		
		} catch (Exception e) {
			
		}
		return result;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getStatusString() {
		if(status == Commons.INVOICE_STATUS_PENDING) {
			return Commons.INVOICE_STATUS_STR_PENDING;
		} 
		return Commons.INVOICE_STATUS_STR_PROCESSED;
	}

	@JsonIgnore
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Long getProcessedInvoiceId() {
		return processedInvoiceId;
	}

	public void setProcessedInvoiceId(Long processedInvoiceId) {
		this.processedInvoiceId = processedInvoiceId;
	}

	/**
	 * @return the processedInvoiceNo
	 */
	public String getProcessedInvoiceNo() {
		return processedInvoiceNo;
	}

	/**
	 * @param processedInvoiceNo the processedInvoiceNo to set
	 */
	
	public void setProcessedInvoiceNo(String processedInvoiceNo) {
		this.processedInvoiceNo = processedInvoiceNo;
	}
	
	

	/**
	 * @return the centerVendorId
	 */
	public Long getCenterVendorId() {
		return centerVendorId;
	}
	/**
	 * @param centerVendorId the centerVendorId to set
	 */
	public void setCenterVendorId(Long centerVendorId) {
		this.centerVendorId = centerVendorId;
	}
	/**
	 * @return the centerVendorName
	 */
	public String getCenterVendorName() {
		return centerVendorName;
	}
	/**
	 * @param centerVendorName the centerVendorName to set
	 */
	public void setCenterVendorName(String centerVendorName) {
		this.centerVendorName = centerVendorName;
	}
	
	
	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "[id=" + id + ",invoiceNo=" + invoiceNo + ",invoiceDate="+ invoiceDate +
		",productname="+ productName+ ",money="+ money + ",type="+ type+
		",status="+ status + ",note=" + note + ",createDate="+ createDate +
		",modifyDate=" + modifyDate + ",vendorId=" + vendorId + ",proccessedInvoice="+
		processedInvoiceId + "]";		
	}
	@JsonIgnore
	public void copyProperties(InvoiceInfo invoiceInfo){
		SimpleDateFormat sdf = new SimpleDateFormat(Commons.DATE_FORMAT_DD_MM_YYYY);
		if(invoiceInfo != null){
			this.id = invoiceInfo.getId();
			this.invoiceNo = invoiceInfo.getInvoiceNo();
			this.invoiceDate = invoiceInfo.getInvoiceDate() != null? 
					sdf.format(invoiceInfo.getInvoiceDate()): null;
			this.productName = invoiceInfo.getProductName();
			this.money = invoiceInfo.getMoney() != null ? invoiceInfo.getMoney().toString() :"";
			this.status = invoiceInfo.getStatus();
			this.note = invoiceInfo.getNote();
			this.type = invoiceInfo.getType();
			//change with full format
			sdf.applyPattern(Commons.DATE_FORMAT_DD_MM_YYYY_HH_MM_SS);
			this.createDate = invoiceInfo.getCreateDate() != null ?
					sdf.format(invoiceInfo.getCreateDate()): null;
			this.modifyDate = invoiceInfo.getModifyDate() != null ?
					sdf.format(invoiceInfo.getModifyDate()): null;
			if(invoiceInfo.getProcessedInvoiceInfo() != null) {					
				this.processedInvoiceId =  invoiceInfo.getProcessedInvoiceInfo().getId();
				this.processedInvoiceNo =  invoiceInfo.getProcessedInvoiceInfo().getInvoiceNo();
			}
			//set vendor
			UserInfo vendorInfo = invoiceInfo.getVendorInfo();
			if(vendorInfo != null) {
				this.vendorId = vendorInfo.getId();
				this.vendorName = vendorInfo.getUsername();
			}
			//set center vendor
			UserInfo centerVendorInfo = invoiceInfo.getCenterVendorInfo();
			if(centerVendorInfo != null) {
				this.centerVendorName = centerVendorInfo.getUsername();
				this.centerVendorId = centerVendorInfo.getId();
			}
			//category
			CategoryInfo categoryInfo = invoiceInfo.getCategoryInfo();
			if(categoryInfo != null) {
				this.categoryName = categoryInfo.getName();
				this.categoryId = categoryInfo.getId();
			}
			UserInfo authorInfo = invoiceInfo.getAuthorInfo();
			if(authorInfo != null) {
				this.authorId = authorInfo.getId();
				this.authorName = authorInfo.getUsername();
			}
		}
	}	
	//valid data
	@JsonIgnore
	public Map<String, String> checkValid(UserService userService,InvoiceService invoiceService,RequestContext ctx){
		Map<String , String> errors = new HashMap<String, String>();
		if(StringUtils.isBlank(invoiceNo)) {
			errors.put("invoiceNo", ctx.getMessage("error.invoice.invoice_no.required"));
		}
		if(StringUtils.isBlank(invoiceDate)) {
			errors.put("invoiceDate",  ctx.getMessage("error.invoice.invoice_date.required"));
		}	
		
		//must valid category name
		if(StringUtils.isBlank(categoryName)) {
			errors.put("categoryName", ctx.getMessage("error.invoice.category.name.required"));
		} else {
			CategoryInfo  categoryInfo =  invoiceService.getCategoryByName(categoryName);
			if(categoryInfo == null) {
				errors.put("categoryName", ctx.getMessage("error.invoice.category.name.notexist"));
			} else {
				categoryId = categoryInfo.getId();
				categoryName = categoryInfo.getName();
			}
		}
		
		//valid for only case central invoice
		if(type == Commons.INVOICE_TYPE_CENTRAL) {					
			//center vendor 
			if(StringUtils.isBlank(centerVendorName)){
				//set default value
				errors.put("centerVendorName", ctx.getMessage("error.invoice.center.vendor.required"));
			} else {
				//check center vendor name
				UserInfo centerVendorInfo = userService.findUserByName(centerVendorName);
				if(centerVendorInfo == null) {
					errors.put("centerVendorName", ctx.getMessage("error.invoice.center.vendor.notexist"));
				} else {
					centerVendorId = centerVendorInfo.getId();
				}				
				//processed invoice
				if(StringUtils.equals(centerVendorName, Commons.CENTER_VENDOR_NAME_DEFAULT)) {
					//empty processed invoice
					processedInvoiceId = null;
					processedInvoiceNo = null;
				} else {					
					if(StringUtils.isBlank(processedInvoiceNo)) {
						errors.put("processedInvoiceNo", ctx.getMessage("error.invoice.processed.required"));
					} else {
						//check exist invoice
						InvoiceInfo invoiceInfo = invoiceService.findInvoiceByNo(processedInvoiceNo);
						if(invoiceInfo == null) {
							errors.put("processedInvoiceNo", ctx.getMessage("error.invoice.processed.notexist"));
						} else {
							processedInvoiceId = invoiceInfo.getId();
						}
					}
					//proccess for vendor name
					if(StringUtils.isBlank(vendorName)) {
						errors.put("vendorName",  ctx.getMessage("error.invoice.vendor.name.required"));
					} else {
						//check vendor name
						//get vendor from vendor name
						UserInfo vendorInfo = userService.findUserByName(vendorName);
						if(vendorInfo == null) {
							errors.put("vendorName",  ctx.getMessage("error.invoice.vendor.name.notexist"));
						} else {
							vendorId = vendorInfo.getId();
							vendorName = vendorInfo.getUsername();
						}
					}
				}	
			}			
								
		} else {
			//vendor
			if(StringUtils.isBlank(vendorName)) {
				errors.put("vendorName",  ctx.getMessage("error.invoice.vendor.name.required"));
			} else {
				//get vendor from vendor name
				UserInfo vendorInfo = userService.findUserByName(vendorName);
				if(vendorInfo == null) {
					errors.put("vendorName",  ctx.getMessage("error.invoice.vendor.name.notexist"));
				} else {
					vendorId = vendorInfo.getId();
					vendorName = vendorInfo.getUsername();
				}
			}
		}		
		if(StringUtils.isBlank(productName)) {
			errors.put("productName",  ctx.getMessage("error.invoice.product.name.required"));
		}
		if(money == null || this.getMoneyValue() == 0) {
			errors.put("money",  ctx.getMessage("error.invoice.money.required"));
		}
		
		return errors;
	}

}
